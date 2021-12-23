import re
import numpy as np
from scipy import stats

sentinel = -1000000


input_arr_str = []
input_arr_chars = []

with open('day_23_input.txt') as file:
    for line in file:
        line = line.strip()
        input_arr_str.append(line)
        input_arr_chars.append(list(line))

print(len(input_arr_str))