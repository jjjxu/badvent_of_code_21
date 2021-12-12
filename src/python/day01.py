import numpy as np

with open('day_1_input.txt') as file:
    count_a = 0
    count_b = 0

    ago = np.array([float('inf')] * 3)
    for line in file:
        number = int(line)
        if number > ago[0]:
            count_a += 1

        if (number + np.sum(ago[0:-1])) > (np.sum(ago)):
            count_b += 1

        ago[2] = ago[1]
        ago[1] = ago[0]
        ago[0] = number

    print(count_a)
    print(count_b)
