import re
import numpy as np
from scipy import stats
import time

sentinel = -1000000


input_str = ""
input_arr_chars = []
lookup = {}
merge_str = ""
new_str = ""
first_line = True
second_line = True

with open('day_14_input.txt') as file:
    for line in file:
        if first_line:
            first_line = False
            input_str = line.strip()
            continue
        elif second_line:
            second_line = False
            continue


        line = line.strip()
        line_split = line.split(' -> ')
        lookup[line_split[0]] = line_split[1]

for i in range(40):
    print("INDEX" + str(i))
    print(len(input_str))
    print("TIMING")
    old_milliseconds = 0
    milliseconds = int(round(time.time() * 1000))
    merge_str = list(" " * (len(input_str) - 1))

    input_str = list(input_str)
    for k in range(len(input_str) - 1):
        test_pair = input_str[k] + input_str[k + 1]

        # old_milliseconds = milliseconds
        # milliseconds = int(round(time.time() * 1000))
        # print(milliseconds - old_milliseconds)

        if test_pair in lookup:
            merge_str[k] = lookup[test_pair]

    k = 0
    new_str = list(" " * (2 * len(merge_str) + 1))

    old_milliseconds = milliseconds
    milliseconds = int(round(time.time() * 1000))
    print(milliseconds - old_milliseconds)



    for k in range(len(merge_str)):
        new_str[k * 2] = input_str[k]
        new_str[k * 2 + 1] = merge_str[k]

    old_milliseconds = milliseconds
    milliseconds = int(round(time.time() * 1000))
    print(milliseconds - old_milliseconds)

    new_str = "".join(new_str)
    new_str = new_str.replace(" ", "")
    new_str += input_str[-1]
    input_str = new_str

    old_milliseconds = milliseconds
    milliseconds = int(round(time.time() * 1000))
    print(milliseconds - old_milliseconds)

counter = {}
for i in range(len(input_str)):
    if input_str[i] in counter:
        counter[input_str[i]] += 1
    else:
        counter[input_str[i]] = 1

counts = []
for k in counter:
    counts.append(counter[k])

counts.sort()

print(counts[-1] - counts[0])