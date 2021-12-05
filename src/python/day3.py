import numpy as np
from scipy import stats

with open('day_3_input.txt') as file:
    data = file.read().splitlines()
    data_splittified = np.array(list(map(list, data))).astype(int)

    # Part a
    mode_data = stats.mode(data_splittified)[0]
    power_2 = mode_data.shape[1]
    number_str = "".join(mode_data[0].astype(str).tolist())
    number = int(number_str, 2)
    print(number * (2 ** power_2 - 1 - number))

    # Part b
    oxy = np.copy(data_splittified)
    co2 = np.copy(data_splittified)
    for i in range(power_2):
        if len(oxy) > 1:
            mode_oxy = stats.mode(oxy)[0][0]
            to_keep = mode_oxy[i]
            if stats.mode(oxy)[1][0][i] * 2 == len(oxy):
                to_keep = 1
            oxy = oxy[oxy[:, i] == to_keep]
        if len(co2) > 1:
            mode_co2 = stats.mode(co2)[0][0]
            to_remove = mode_co2[i]
            if stats.mode(co2)[1][0][i] * 2 == len(co2):
                to_remove = 1
            co2 = co2[co2[:, i] != to_remove]
    oxy_str = "".join(oxy[0].astype(str).tolist())
    co2_str = "".join(co2[0].astype(str).tolist())

    print(int(oxy_str, 2) * int(co2_str, 2))
