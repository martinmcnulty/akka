#!/bin/bash

for i in {1..10}; do
    echo "Iteration $i"
    bloop clean
    bloop compile $1
done
