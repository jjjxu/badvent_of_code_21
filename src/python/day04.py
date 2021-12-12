import re
import numpy as np

sentinel = -1000000


class Bingo:

    def __init__(self, gameboard):
        self.board = np.copy(gameboard)
        self.empty_board = np.copy(gameboard)
        self.last_val = sentinel
        self.last_empty = sentinel

    def insert(self, insertion_number):
        coord = np.asarray(np.where(self.board == insertion_number)).T.tolist()
        if len(coord) == 0:
            return
        coord = coord[0]
        self.empty_board[coord[0]][coord[1]] = 0
        if self.last_empty == sentinel:
            if 0 in np.sum(self.empty_board, axis=0) or 0 in np.sum(self.empty_board, axis=1):
                self.last_val = insertion_number
                self.last_empty = np.sum(np.where(self.empty_board == sentinel, 0, self.empty_board))
                print(self.last_val * self.last_empty)


with open('day_4_input.txt') as file:
    data = file.read().splitlines()
    numbers = np.array(data[0].split(',')).astype(int).tolist()
    numbers = np.where(np.array(numbers) == 0, sentinel, numbers).tolist()
    bingos = []
    for i in range(int((len(data) - 1) / 6)):
        # line 0 * i + 1 will be blank
        board = []
        for j in range(5):
            board.append(np.array(re.split(r" +", data[i * 6 + j + 2].strip())).astype(int).tolist())
        bingos.append(Bingo(np.where(np.array(board) == 0, sentinel, board)))

    for number in numbers:
        for bingo in bingos:
            bingo.insert(number)
