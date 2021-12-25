import re
import numpy as np
from scipy import stats

sentinel = -1000000

input_arr_str = []

with open('day_25_input.txt') as file:
    for line in file:
        line = line.strip()
        line_new = line.replace("v", "9")
        line_new = line_new.replace(".", "0")
        line_new = line_new.replace(">", "1")

        input_arr_str.append(list(line_new))

input_arr_str = np.array(input_arr_str).astype(int)
new_arr = np.zeros((len(input_arr_str), len(input_arr_str[0]))).astype(int)

counter = 0
while True:
    status = False
    print("still running", str(counter))
    counter += 1
    new_arr = np.zeros((len(input_arr_str), len(input_arr_str[0]))).astype(int)
    dim = input_arr_str.shape
    for y in range(dim[0]):
        for x in range(dim[1]):
            if input_arr_str[y][x] == 0:
                continue
            elif input_arr_str[y][x] == 1:
                x_new = (x + 1) % (dim[1])
                y_new = y
                if input_arr_str[y_new][x_new] == 0:
                    new_arr[y_new][x_new] = 1
                    input_arr_str[y][x] = -1
                    status = True
                else:
                    new_arr[y][x] = 1
            else:
                new_arr[y][x] = input_arr_str[y][x]
            # elif input_arr_str[x][y] == 9:
                # x_new = x
                # y_new = (y + 1) % (dim[1])
                # if input_arr_str[x_new][y_new] == 0:
                #     new_arr[x_new][y_new] = 9
                # else:
                #     new_arr[x][y] = 9

    halfway = np.copy(new_arr)
    new_arr = np.zeros((len(input_arr_str), len(input_arr_str[0]))).astype(int)

    for y in range(dim[0]):
        for x in range(dim[1]):
            if halfway[y][x] == 0:
                continue
            elif halfway[y][x] == 9:
                x_new = x
                y_new = (y + 1) % (dim[0])
                if halfway[y_new][x_new] == 0:
                    new_arr[y_new][x_new] = 9
                    halfway[y][x] = -1
                    status = True
                else:
                    new_arr[y][x] = 9
            else:
                new_arr[y][x] = halfway[y][x]

    if not status:
        break
    input_arr_str = np.copy(new_arr)

print(counter)
