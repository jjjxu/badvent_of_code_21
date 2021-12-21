import re
import numpy as np
from scipy import stats

sentinel = -1000000


input_arr_str = []
input_arr_chars = []

players_raw = []
players_score = []

with open('day_21_input.txt') as file:
    for line in file:
        line = line.strip()
        line_split = line.split("starting position: ")
        number = int(line_split[1])
        players_raw.append(number)
        players_score.append(0)


players = np.array(players_raw)
dice_count = 1
actual_dice_count = 0
while max(players_score) < 1000:
    for i in range(len(players)):
        new_pos = 0
        for j in range(3):
            new_pos += dice_count
            dice_count += 1
            actual_dice_count += 1
            if dice_count > 100:
                dice_count = 1
        players[i] = (players[i] + new_pos)
        if players[i] % 10 == 0:
            players[i] = 10
        else:
            players[i] = players[i] % 10
        players_score[i] += players[i]
        if players_score[i] >= 1000:
            break


print(min(players_score) * actual_dice_count)

player_scores = {}
player_scores[(players_raw[0], players_raw[1], 0, 0)] = 1
winnings = [0, 0]
# 3, 4, 5, 6, 7, 8, 9
# 1, 3, 6, 7, 6, 3, 1
condition = True
while len(player_scores) > 0:
    new_player_scores = {}
    for key in player_scores.keys():
        possibilities = [3, 4, 5, 6, 7, 8, 9]
        multipliers = [1, 3, 6, 7, 6, 3, 1]

        for one in range(len(possibilities)):
            possibles = player_scores[key] * multipliers[one]
            new_one = key[0] + possibilities[one]
            if new_one % 10 == 0:
                new_one = 10
            else:
                new_one %= 10

            new_one_score = key[2] + new_one
            if new_one_score >= 21:
                winnings[0] += possibles
                continue
            for two in range(len(possibilities)):
                possible_two = possibles * multipliers[two]

                new_two = key[1] + possibilities[two]
                if new_two % 10 == 0:
                    new_two = 10
                else:
                    new_two %= 10
                new_two_score = key[3] + new_two
                if new_two_score >= 21:
                    winnings[1] += possible_two
                    continue
                new_key = (new_one, new_two, new_one_score, new_two_score)
                if new_key not in new_player_scores:
                    new_player_scores[new_key] = possible_two
                else:
                    new_player_scores[new_key] += possible_two


        # for one in range(len(possibilities)):
        #     for two in range(len(possibilities)):
        #         possibles = player_scores[key] * multipliers[one]
        #         new_one = key[0] + possibilities[one]
        #         if new_one != 10:
        #             new_one = new_one % 10
        #         new_one_score = key[2] + new_one
        #         if new_one_score >= 21:
        #             winnings[0] += possibles
        #             continue
        #         possibles *= multipliers[two]
        #         new_two = key[1] + possibilities[two]
        #         if new_two != 10:
        #             new_two = new_two % 10
        #         new_two_score = key[3] + new_two
        #         if new_two_score >= 21:
        #             winnings[1] += possibles
        #             continue
        #         new_key = (new_one, new_two, new_one_score, new_two_score)
        #         if new_key not in new_player_scores:
        #             new_player_scores[new_key] = possibles
        #         else:
        #             new_player_scores[new_key] += possibles
    player_scores = new_player_scores

print(winnings)
print(max(winnings))

