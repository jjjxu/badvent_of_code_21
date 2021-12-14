import re
import numpy as np
from scipy import stats
import time

sentinel = -1000000

input_str = ""
input_arr_chars = []
lookup = {}
lookup_cnt = {}
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

for k in range(len(input_str) - 1):
    test_pair = input_str[k] + input_str[k + 1]
    if test_pair in lookup_cnt:
        lookup_cnt[test_pair] += 1
    else:
        lookup_cnt[test_pair] = 1

for i in range(40):
    print(i)
    new_cnt = {}
    for pair in lookup_cnt.keys():
        count = lookup_cnt[pair]
        new_insert = lookup[pair]

        new_left = pair[0] + new_insert
        new_right = new_insert + pair[1]

        if new_left in new_cnt:
            new_cnt[new_left] += count
        else:
            new_cnt[new_left] = count

        if new_right in new_cnt:
            new_cnt[new_right] += count
        else:
            new_cnt[new_right] = count
    lookup_cnt = new_cnt

counter = {}
for pair in lookup_cnt.keys():
    count = lookup_cnt[pair]

    letter = pair[0]
    if letter in counter:
        counter[letter] += count
    else:
        counter[letter] = count

counter[input_str[-1]] += 1

counts = []
for k in counter:
    counts.append(counter[k])

counts.sort()

print((counts[-1] - counts[0]))
