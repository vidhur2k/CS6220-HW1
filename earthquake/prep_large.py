import os
import sys

with open('Earthquake.txt', 'r') as f:
    lines = f.readlines()
    
    with open('Earthquake-large.txt', 'w') as wf:
        for i in range(1, len(lines)):
            for j in range(10000):
                wf.write(lines[i])

        wf.close()
    
    f.close()