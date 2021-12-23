import re
import numpy as np
from scipy import stats

sentinel = -1000000

status_cubes = {}
test = 0

with open('day_22_input.txt') as file:
    for line in file:
        line = line.strip()

        line_data = line.split(' ')
        on_off = line_data[0] == 'on'
        line_data = line_data[1]
        cubes = line_data.split(',')
        cubes_coords = []

        skip = False
        for cube in cubes:
            cube_coords = cube.split('=')
            bounds = cube_coords[1].split('..')
            lo = int(bounds[0])
            hi = int(bounds[1])
            cubes_coords.append((lo, hi))
            if hi < -50:
                skip = True
                break
            if lo > 50:
                skip = True
                break
        if skip:
            continue

        for i in range(cubes_coords[0][0], cubes_coords[0][1] + 1):
            for j in range(cubes_coords[1][0], cubes_coords[1][1] + 1):
                for k in range(cubes_coords[2][0], cubes_coords[2][1] + 1):
                    if np.abs(i) > 50:
                        continue
                    if np.abs(j) > 50:
                        continue
                    if np.abs(k) > 50:
                        continue

                    if on_off:
                        status_cubes[(i, j, k)] = 1
                    else:
                        status_cubes[(i, j, k)] = 0

tot = 0
for i in status_cubes:
    tot += status_cubes[i]

print(tot)

test = 0
total = 0
prev_coords = []

inputs = []

xs = {}
ys = {}
zs = {}
with open('day_22_input.txt') as file:
    for line in file:
        line = line.strip()

        line_data = line.split(' ')
        on_off = line_data[0] == 'on'
        line_data = line_data[1]
        cubes = line_data.split(',')
        cubes_coords = []

        skip = False
        for cube in cubes:
            cube_coords = cube.split('=')
            bounds = cube_coords[1].split('..')
            lo = int(bounds[0]) - 1
            hi = int(bounds[1])
            cubes_coords.append([lo, hi])

        xs[cubes_coords[0][0]] = 1
        ys[cubes_coords[1][0]] = 1
        zs[cubes_coords[2][0]] = 1

        xs[cubes_coords[0][1]] = 1
        ys[cubes_coords[1][1]] = 1
        zs[cubes_coords[2][1]] = 1
        inputs.append((on_off, cubes_coords))

x_keys = list(xs.keys())
y_keys = list(ys.keys())
z_keys = list(zs.keys())

x_keys.sort()
y_keys.sort()
z_keys.sort()

mini_cubes = {}
for x in range(len(x_keys) - 1):
    for y in range(len(y_keys) - 1):
        for z in range(len(z_keys) - 1):
            mini_cubes[(x_keys[x], x_keys[x + 1], y_keys[y], y_keys[y + 1], z_keys[z], z_keys[z + 1])] = 0
print("your mini cubes are ready for dinner")
tot = 0
counter = 0
for mini_cube in mini_cubes:
    counter += 1
    if counter % 100000 == 0:
        print(tot)
    # print(tot)
    status = False
    for inp in inputs:
        on_off = inp[0]
        cubes_coords = inp[1]
        if mini_cube[0] >= cubes_coords[0][0] and mini_cube[1] <= cubes_coords[0][1] and mini_cube[2] >= cubes_coords[1][0] and mini_cube[3] <= cubes_coords[1][1] and mini_cube[4] >= cubes_coords[2][0]  and mini_cube[5] <= cubes_coords[2][1]:
            # print("jerry no stupid")
            status = on_off

    if status:
        tot += (mini_cube[1] - mini_cube[0]) * (mini_cube[3] - mini_cube[2]) * (mini_cube[5] - mini_cube[4])

print(tot)



