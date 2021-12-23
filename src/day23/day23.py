import re
import numpy as np
from scipy import stats

sentinel = -1000000

data = []

first_two = 0

with open('day_23_input.txt') as file:
    for line in file:
        first_two += 1
        if first_two <= 2:
            continue
        line = line.strip()
        spaces = line.split("#")
        row = []
        for space in spaces:
            if len(space) == 0:
                continue
            row.append(space)
        if len(row) == 0:
            continue
        data.append(row)

print("done parse")
