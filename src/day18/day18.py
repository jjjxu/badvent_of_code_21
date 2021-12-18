import json
import re
import numpy as np
from scipy import stats

sentinel = -1000000


input_arr_str = []
input_arr_chars = []
pairs = []

with open('day_18_input.txt') as file:
    for line in file:
        line = line.strip()
        pair = json.loads(line)

        # stack = []
        # pair = []
        # loc_pair = []
        # level = -1
        # pos = 0
        lvl = -1
        number = []
        level = []
        for char in line:
            if char == '[':
                lvl += 1
            elif char == ']':
                lvl -= 1
            elif char == ',':
                pass
            else:
                number.append(int(char))
                level.append(lvl)

        pairs.append([np.array(number), np.array(level)])


result = pairs[0]
for i in range(1, len(pairs)):
    res_nums = np.concatenate((result[0], pairs[i][0]))
    res_levels = np.concatenate((result[1], pairs[i][1]))
    res_levels = res_levels + 1
    while not(np.max(res_nums) < 10 and len(res_levels[res_levels >= 4]) == 0):
        trigger = False
        if len(res_levels[res_levels >= 4]) != 0:
            for c in range(len(res_nums) - 1):
                if 4 <= res_levels[c] == res_levels[c + 1]:
                    if c >= 1:
                        res_nums[c - 1] += res_nums[c]
                    if c + 2 < len(res_nums):
                        res_nums[c + 2] += res_nums[c + 1]
                    res_nums = np.delete(res_nums, c)
                    res_levels = np.delete(res_levels, c)
                    res_nums[c] = 0
                    res_levels[c] -= 1
                    trigger = True
                    break
        if trigger:
            continue
        if np.max(res_nums) >= 10:
            for c in range(len(res_nums)):
                if res_nums[c] >= 10:
                    val = int(res_nums[c] // 2)
                    val_mod = int(res_nums[c] // 2) + res_nums[c] % 2
                    res_nums[c] = val
                    res_levels[c] += 1
                    res_nums = np.insert(res_nums, c + 1, val_mod)
                    res_levels = np.insert(res_levels, c + 1, res_levels[c])
                    break

    result = [res_nums, res_levels]

result_levels = result[1]
result_nums = result[0]
# result_levels = [0, 0]
# result_nums = [9, 1]
for i in reversed(range(3 + 1)):
    while np.max(result_levels) == i:
        for c in range(len(result_levels) - 1):
            if result_levels[c] == result_levels[c + 1] == i:
                overwrite = result_nums[c] * 3 + result_nums[c + 1] * 2
                result_nums = np.delete(result_nums, c)
                result_levels = np.delete(result_levels, c)
                result_nums[c] = overwrite
                result_levels[c] -= 1
                break
print(result_nums)

max_result = 0
for i in range(len(pairs)):
    for j in range(len(pairs)):
        # print((i, j))
        if i == j:
            continue
        res_nums = np.concatenate((pairs[j][0], pairs[i][0]))
        res_levels = np.concatenate((pairs[j][1], pairs[i][1]))
        res_levels = res_levels + 1
        while not(np.max(res_nums) < 10 and len(res_levels[res_levels >= 4]) == 0):
            trigger = False
            if len(res_levels[res_levels >= 4]) != 0:
                for c in range(len(res_nums) - 1):
                    if 4 <= res_levels[c] == res_levels[c + 1]:
                        if c >= 1:
                            res_nums[c - 1] += res_nums[c]
                        if c + 2 < len(res_nums):
                            res_nums[c + 2] += res_nums[c + 1]
                        res_nums = np.delete(res_nums, c)
                        res_levels = np.delete(res_levels, c)
                        res_nums[c] = 0
                        res_levels[c] -= 1
                        trigger = True
                        break
            if trigger:
                continue
            if np.max(res_nums) >= 10:
                for c in range(len(res_nums)):
                    if res_nums[c] >= 10:
                        val = int(res_nums[c] // 2)
                        val_mod = int(res_nums[c] // 2) + res_nums[c] % 2
                        res_nums[c] = val
                        res_levels[c] += 1
                        res_nums = np.insert(res_nums, c + 1, val_mod)
                        res_levels = np.insert(res_levels, c + 1, res_levels[c])
                        break

        result = [res_nums, res_levels]

        result_levels = result[1]
        result_nums = result[0]
        # result_levels = [0, 0]
        # result_nums = [9, 1]
        for k in reversed(range(3 + 1)):
            while np.max(result_levels) == k:
                for c in range(len(result_levels) - 1):
                    if result_levels[c] == result_levels[c + 1] == k:
                        overwrite = result_nums[c] * 3 + result_nums[c + 1] * 2
                        result_nums = np.delete(result_nums, c)
                        result_levels = np.delete(result_levels, c)
                        result_nums[c] = overwrite
                        result_levels[c] -= 1
                        break
        if result_nums[0] > max_result:
            max_result = result_nums[0]
print(max_result)