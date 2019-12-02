#!/bin/env python3
# Build all java files in folder and run Main.java
import os
import subprocess

# Find all .java files in folder
javaFiles = [file for file in os.listdir() if os.path.splitext(file)[1] == '.java']

# Compile
print(f"Compiling {['javac'] + javaFiles}")
subprocess.run(['javac'] + javaFiles)

# Run
print("Running")
subprocess.run(['java', 'Main'])
