import numpy as np

with open('day_2_input.txt') as file:
    pos_a = np.array([0] * 2)
    pos_b = np.array([0] * 2)
    aim_b = 0

    for line in file:
        splitify = line.split(' ')
        if splitify[0] == 'forward':
            pos_a[0] += int(splitify[1])
            pos_b[0] += int(splitify[1])
            pos_b[1] += int(splitify[1]) * aim_b
        elif splitify[0] == 'down':
            pos_a[1] += int(splitify[1])
            aim_b += int(splitify[1])
        elif splitify[0] == 'up':
            pos_a[1] -= int(splitify[1])
            aim_b -= int(splitify[1])

    print(pos_a[0] * pos_a[1])
    print(pos_b[0] * pos_b[1])
