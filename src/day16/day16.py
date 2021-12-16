import re
import numpy as np
from scipy import stats

sentinel = -1000000

stupid_line = ""
stupid_binary = ""
input_arr_str = []
input_arr_chars = []

with open('day_16_input.txt') as file:
    for line in file:
        line = line.strip()
        input_arr_str.append(line)
        input_arr_chars.append(list(line))
        stupid_line = line

end_length = len(stupid_line) * 4
hex_as_int = int(stupid_line, 16)
hex_as_bin = bin(hex_as_int)
stupid_binary = hex_as_bin[2:].zfill(end_length)

version_sum = 0


def parse_string(stupid_binary):
    global version_sum
    i = 0
    while i < (len(stupid_binary)):
        jerry_version = stupid_binary[i:i+3]
        i += 3
        version_sum += int(jerry_version, 2)

        jerry_type = stupid_binary[i:i+3]
        i += 3
        if jerry_type == "100":
            one_bit = True
            result = ""
            while one_bit:
                signal_bit = stupid_binary[i]
                i += 1
                next_bits = stupid_binary[i:i+4]
                result = result + next_bits
                i += 4
                if signal_bit == "0":
                    one_bit = False
                    return stupid_binary[i:], int(result, 2)
            break
        else:
            len_type = stupid_binary[i]
            i += 1
            if len_type == "0":
                packet_len = int(stupid_binary[i:i+15], 2)
                i += 15
                new_str = stupid_binary[i:i + packet_len]
                results = []
                while len(new_str) > 0:
                    new_str, new_result = parse_string(new_str)
                    results.append(new_result)
                if jerry_type == "000":
                    return stupid_binary[i + packet_len:], sum(results)
                elif jerry_type == "001":
                    np_results = np.array(results)
                    return stupid_binary[i + packet_len:], np.prod(np_results)
                elif jerry_type == "010":
                    return stupid_binary[i + packet_len:], min(results)
                elif jerry_type == "011":
                    return stupid_binary[i + packet_len:], max(results)
                elif jerry_type == "101":
                    return stupid_binary[i + packet_len:], int(results[0] > results[1])
                elif jerry_type == "110":
                    return stupid_binary[i + packet_len:], int(results[0] < results[1])
                elif jerry_type == "111":
                    return stupid_binary[i + packet_len:], int(results[0] == results[1])
            else:
                num_packets = int(stupid_binary[i:i+11], 2)
                i += 11
                new_str = stupid_binary[i:]
                results = []
                for j in range(num_packets):
                    new_str, new_result = parse_string(new_str)
                    results.append(new_result)

                if jerry_type == "000":
                    return new_str, sum(results)
                elif jerry_type == "001":
                    np_results = np.array(results)
                    return new_str, np.prod(np_results)
                elif jerry_type == "010":
                    return new_str, min(results)
                elif jerry_type == "011":
                    return new_str, max(results)
                elif jerry_type == "101":
                    return new_str, int(results[0] > results[1])
                elif jerry_type == "110":
                    return new_str, int(results[0] < results[1])
                elif jerry_type == "111":
                    return new_str, int(results[0] == results[1])

# stupid_binary = r"00111000000000000110111101000101001010010001001000000000"

ps, res = parse_string(stupid_binary)


print(version_sum)
print(res)
