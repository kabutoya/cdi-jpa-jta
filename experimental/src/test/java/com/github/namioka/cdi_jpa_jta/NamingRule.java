package com.github.namioka.cdi_jpa_jta;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionSynchronizationRegistryImple;
import com.arjuna.ats.internal.jta.transaction.arjunacore.UserTransactionImple;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.jnp.interfaces.NamingContext;
import org.jnp.interfaces.NamingContextFactory;
import org.jnp.server.NamingServer;
import org.junit.rules.ExternalResource;

public class NamingRule extends ExternalResource {

    private static final NamingServer inmemoryNamingServer;

    static {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, NamingContextFactory.class.getName());
        System.setProperty(Context.URL_PKG_PREFIXES, NamingContextFactory.class.getPackage().getName());
        try {
            inmemoryNamingServer = new NamingServer();
            NamingContext.setLocal(inmemoryNamingServer);
        } catch (NamingException cause) {
            throw new RuntimeException(cause);
        }
    }

    @Override
    protected void before() throws Throwable {
        final InitialContext ic = new InitialContext();
        // com.arjuna.ats.jta.utils.JNDIManager.bindJTAImplementations(ic);
        // -> Actually the following...
        final String transactionManagerClassName = TransactionManagerImple.class.getName();
        final String userTransactionClassName = UserTransactionImple.class.getName();
        ic.bind("java:/TransactionManager", new Reference(transactionManagerClassName, transactionManagerClassName, null));
        ic.bind("java:/UserTransaction", new Reference(userTransactionClassName, userTransactionClassName, null));
        ic.bind("java:/TransactionSynchronizationRegistry", TransactionSynchronizationRegistryImple.class.newInstance());

        final DataSource ds = new EmbeddedDataSource();
        ((EmbeddedDataSource) ds).setDatabaseName("memory:test_DB;create=true");
        ic.bind("java:/test_DS", ds);
    }

    @Override
    protected void after() {
        NamingContext.setLocal(null);
    }
}
