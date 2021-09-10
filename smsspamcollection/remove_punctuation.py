import os
import sys

if len(sys.argv) < 2:
    raise ValueError('Please specify a directory.')

DIR = sys.argv[1]
for f in os.listdir(DIR):
    with open(os.path.join(DIR, f), 'r') as in_file:
        lines = in_file.readlines()
        with open(os.path.join(DIR, f), 'w') as out_file:
            for line in lines:
                out_file.write(''.join(c for c in line if c .isalnum() or c == ' '))