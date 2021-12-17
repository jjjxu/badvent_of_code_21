import re
import numpy as np
from scipy import stats

sentinel = -1000000


input_arr_str = []
input_arr_chars = []

data_x = []
data_y = []
y_min = 0

with open('day_17_input.txt') as file:
    for line in file:
        line = line.strip()
        line = line.split(":")
        line = line[1].strip()
        line = line.split(", ")

        line_x = line[0].strip()
        d_x = line_x.split("=")[1]
        d_x = d_x.split("..")
        data_x.append(int(d_x[0]))
        data_x.append(int(d_x[1]))
        x_max = max(data_x[0], data_x[1])

        line_y = line[1].strip()
        d_y = line_y.split("=")[1]
        d_y = d_y.split("..")
        data_y.append(int(d_y[0]))
        data_y.append(int(d_y[1]))
        y_min = min(data_y[0], data_y[1])

y_max = 0
y_maxes = {}
rg = 1000
x_rg = max(data_x)
for x_try in range(min(0, x_rg) - 1, max(0, x_rg) + 1):
    if x_try % 10 == 0:
        print(x_try)
    for y_try in range(-rg, rg):
        y_m = 0
        x_speed = x_try
        y_speed = y_try
        pos = [0, 0]

        while pos[0] < x_max and pos[1] > y_min:

            pos[0] += x_speed
            pos[1] += y_speed
            if pos[1] > y_m:
                y_m = pos[1]
            if x_speed > 0:
                x_speed -= 1
            elif x_speed < 0:
                x_speed += 1

            y_speed -= 1
            if min(data_x) <= pos[0] <= max(data_x) and min(data_y) <= pos[1] <= max(data_y):
                if y_m > y_max:
                    y_max = y_m
                y_maxes[(x_try, y_try)] = "jerry's fucking stupid"



print(y_max)

print(len(y_maxes))

