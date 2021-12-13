import re
import numpy as np
from scipy import stats

sentinel = -1000000

paper = np.zeros((2000, 2000)).astype(int)
input_arr_str = []
input_arr_chars = []

with open('day_13_input.txt') as file:
    before_folds = True
    for line in file:
        line = line.strip()
        if len(line) == 0:
            before_folds = False
            continue

        if before_folds:
            line_split = line.split(',')
            x = int(line_split[0])
            y = int(line_split[1])
            paper[y, x] = 1
            continue

        if not before_folds:
            input_arr_str.append(line)
            line = line.split(' ')
            coord = line[2].split("=")
            dir = coord[0]
            equals = int(coord[1])
            if dir == 'x':
                for x in range(equals, 2000):
                    for y in range(0, 2000):
                        if paper[y, x] == 1:
                            paper[y, x] = 0
                            paper[ y, (equals) - (x - equals)] = 1
            elif dir == 'y':
                for y in range(equals, 2000):
                    for x in range(0, 2000):
                        if paper[y, x] == 1:
                            paper[y, x] = 0
                            paper[equals - (y - equals), x] = 1
            print(int(np.sum(paper)))

print("hello")