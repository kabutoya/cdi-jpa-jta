http://martinfowler.com/apsupp/spec.pdf

/---------------------------------\
|(A) /-------------\     /--------:----\
|    |(D)      /---:-----:---\    : (B)|
|    |         |   : (C) :   |    :    |
|    |         \---:-----:---/    :    |
|    \-------------/     \--------:----/
\---------------------------------/

A   isSpecialCaseOf(subsumes)   isGeneralizationOf
------------------------------------------------------------
A   true                        true
B   false                       false
C   true                        false
D   true                        false

B   isSpecialCaseOf(subsumes)   isGeneralizationOf
------------------------------------------------------------
A   false                       false
B   true                        true
C   false                       false
D   false                       false

C   isSpecialCaseOf(subsumes)   isGeneralizationOf
------------------------------------------------------------
A   false                       true
B   false                       false
C   true                        true
D   false                       false

D   isSpecialCaseOf(subsumes)   isGeneralizationOf
------------------------------------------------------------
A   false                       true
B   false                       false
C   false                       false
D   true                        true
