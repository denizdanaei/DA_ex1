#!/bin/env python3
# Build all java files in folder and run Main.java
import os
import subprocess

# Find all .java files in folder
javaFiles = []
for file in os.listdir():
    base, ext = os.path.splitext(file)
    if ext == '.java':
        javaFiles.append(file)

# Compile
print(f"Compiling: {['javac'] + javaFiles}")
subprocess.run(['javac'] + javaFiles)

# Run
subprocess.run(['java', 'Main'])
