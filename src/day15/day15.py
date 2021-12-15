import re
import numpy as np
from scipy import stats
import heapq

sentinel = 1000000000000000
size = 100

input_arr_str = []
input_arr_chars = []
cave_map = []
cave_tot = np.zeros((size, size)).astype(int)
cave_maps = [np.zeros((size, size)).astype(int)] * 8

with open('day_15_input.txt') as file:
    # cave_map = np.array(cave_map)
    for line in file:
        line = line.strip()
        cave_line = [0] * size
        for i, char in enumerate(line):
            cave_line[i] = int(char)
        cave_map.append(cave_line)

cave_map = np.array(cave_map).astype(int)
for i in range(8):
    temp_map = cave_maps[i]
    for x in range(size):
        for y in range(size):
            new_pos = cave_map[x][y] + i + 1
            if new_pos > 9:
                new_pos -= 9
            temp_map[x][y] = new_pos
    cave_maps[i] = np.array(temp_map).astype(int)

big = np.concatenate((cave_map, cave_maps[0], cave_maps[1], cave_maps[2], cave_maps[3]), axis=0)
for i in range(4):
    real_i = i
    big_row = np.concatenate((cave_maps[real_i], cave_maps[real_i + 1], cave_maps[real_i + 2], cave_maps[real_i + 3], cave_maps[real_i + 4]), axis=0)
    big = np.concatenate((big, big_row), axis=1)

print("a")


for i in range(size * 2):
    if i == 0:
        cave_tot[0][0] = 0
        continue
    for j in range(i + 1):
        x = j
        y = i - j
        prev = [ (-1, 0), (0, -1)]
        if x < 0 or y < 0 or x >= size or y >= size:
            continue
        incr = sentinel
        for k in range(len(prev)):
            x_new = x + prev[k][0]
            y_new = y + prev[k][1]
            if x_new < 0 or y_new < 0 or x_new >= size or y_new >= size:
                continue
            if cave_tot[y_new][x_new] < incr:
                incr = cave_tot[y_new][x_new]
        cave_tot[y][x] = incr + cave_map[y][x]

print(cave_tot[size - 1][size - 1])

big_cave_tot = np.zeros((size * 5, 5 * size)).astype(int)

# for i in range(size * 10 + 1):
#     if i == 0:
#         cave_tot[0][0] = 0
#         continue
#     for j in range(i + 1):
#         x = j
#         y = i - j
#         prev = [ (-1, 0), (0, -1)]
#         if x < 0 or y < 0 or x >= size * 5 or y >= size * 5:
#             continue
#         incr = sentinel
#         for k in range(len(prev)):
#             x_new = x + prev[k][0]
#             y_new = y + prev[k][1]
#             if x_new < 0 or y_new < 0 or x_new >= size * 5 or y_new >= size * 5:
#                 continue
#             if big_cave_tot[y_new][x_new] < incr:
#                 incr = big_cave_tot[y_new][x_new]
#         big_cave_tot[y][x] = incr + big[y][x]

frontier = []
heapq.heappush(frontier, (0, 0, 0))

while len(frontier) > 0:
    next_elem = heapq.heappop(frontier)
    (x, y) = (next_elem[1], next_elem[2])
    neighbors = [(x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)]
    for i in range(len(neighbors)):
        if neighbors[i][0] < 0 or neighbors[i][1] < 0 or neighbors[i][0] >= size * 5 or neighbors[i][1] >= size * 5:
            continue
        if big_cave_tot[neighbors[i][1]][neighbors[i][0]] == 0:
            heapq.heappush(frontier, (next_elem[0] + big[neighbors[i][1]][neighbors[i][0]], neighbors[i][0], neighbors[i][1]))
            big_cave_tot[neighbors[i][1]][neighbors[i][0]] = next_elem[0] + big[neighbors[i][1]][neighbors[i][0]]



print("b")

print(big_cave_tot[size * 5 - 1][size * 5 - 1])