import re
import numpy as np
from scipy import stats

sentinel = -1000000


input_arr_str = []
input_arr_chars = []

adj_matrix = []

rooms = {}
rooms_lookup = {}
visits = {}

def visit(room, visited, big_visited, small_visited, count, double_visit):
    retCount = 0
    old_room = rooms_lookup[room]
    visited += "," + old_room
    for i in range(len(all_rooms)):
        curr_room = rooms_lookup[i]

        if adj_matrix[room][i] == 1:
            if curr_room == "start":
                continue
            elif curr_room == "end":
                print(visited)
                visits[visited] = 0
                retCount += 1
            elif curr_room.lower() == curr_room:
                copy_visited = small_visited.copy()
                if small_visited[i] == 0:
                    copy_visited[i] = 1
                    retCount += visit(i, visited, big_visited, copy_visited, count, double_visit)
                # to do part a, comment this line out
                if not double_visit:
                    retCount += visit(i, visited, big_visited, copy_visited, count, True)

            elif curr_room.upper() == curr_room:
                retCount += visit(i, visited, big_visited, small_visited, count, double_visit)
    return retCount + count



with open('day_12_input.txt') as file:
    for line in file:
        line = line.strip()
        input_arr_str.append(line)
        input_arr_chars.append(list(line))
        rums = line.split('-')
        fst = rums[0].strip()
        snd = rums[1].strip()
        rooms[fst] = 0
        rooms[snd] = 0

    all_rooms = list(rooms.keys())
    all_rooms.sort()
    for i in range(len(all_rooms)):
        rooms[all_rooms[i]] = i
        rooms_lookup[i] = all_rooms[i]
    adj_matrix = np.zeros((len(all_rooms), len(all_rooms)))


with open('day_12_input.txt') as file:
    for line in file:
        rums = line.split('-')
        fst = rums[0].strip()
        snd = rums[1].strip()
        adj_matrix[rooms[fst]][rooms[snd]] = 1
        adj_matrix[rooms[snd]][rooms[fst]] = 1

start = rooms['start']
end = rooms['end']

total = 0

for i in range(len(all_rooms)):
    curr_room = rooms_lookup[i]
    big_visited = np.zeros(len(all_rooms))
    small_visited = np.zeros(len(all_rooms))
    small_visited[rooms["start"]] = 1
    if not (adj_matrix[i][start]):
        continue
    else:
        big_visited[i] = 1
        if curr_room.lower() == curr_room:
            small_visited[i] = 1
        total += visit(i, "start", big_visited, small_visited, 0, False)

print(total)
print(len(visits.keys()))
