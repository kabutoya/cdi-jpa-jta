#!/bin/bash

for file in $(find -type f -name *.java)
do
    echo ${file}
    grep -v '^\s*$' ${file} > ${file}.tmp
    mv ${file}.tmp ${file}
done

