import re
import numpy as np
from scipy import stats

sentinel = -1000000

enhance_string = ''

first_line = True

data = []

with open('day_20_input.txt') as file:
    for line in file:
        line = line.strip()
        if len(line) == 0:
            continue

        new_str = line.replace(".", "0").replace("#", "1")
        if first_line:
            enhance_string = new_str
            first_line = False
            continue

        row = []
        for char in new_str:
            row.append(int(char))

        data.append(row)

data = np.array(data)

print("read done")


def pad_data(inp_data, dist=40):
    data_dim = inp_data.shape

    empty_buffer_row = np.zeros((dist, data_dim[0]))
    inp_data = np.concatenate((empty_buffer_row, inp_data, empty_buffer_row), axis=0)

    data_dim = inp_data.shape

    empty_buffer_col = np.zeros((data_dim[0], dist))
    inp_data = np.concatenate((empty_buffer_col, inp_data, empty_buffer_col), axis=1)

    inp_data = inp_data.astype(int)
    return inp_data


data = pad_data(data, 10)

print("pad done")


def run_img(data):
    data_dim = data.shape
    new_data = np.zeros(data_dim)
    new_data = new_data.astype(int)
    for x in range(data_dim[0]):
        for y in range(data_dim[1]):
            if x == 0 or x == data_dim[0] - 1 or y == 0 or y == data_dim[1] - 1:
                new_data[x][y] = 1 - data[1][1]
                continue
            local_data = data[x - 1:x + 2, y - 1:y + 2]
            local_data = local_data.flatten()
            int_data = local_data.dot(2 ** np.flip(np.arange(9)).astype(int))
            new_data[x, y] = enhance_string[int_data]
    for x in range(data_dim[0]):
        for y in range(data_dim[1]):
            if not(x == 0 or x == data_dim[0] - 1 or y == 0 or y == data_dim[1] - 1):
                continue
            new_data[x][y] = new_data[1][1]
    return new_data

for i in range(25):
    data = run_img(data)
    data = run_img(data)
    data = pad_data(data, 10)
    print(str(i) + " " + str(np.sum(data)))

