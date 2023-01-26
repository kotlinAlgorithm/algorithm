package week5

/**
 * https://www.acmicpc.net/problem/12100
 */
const val LAST_MOVE_COUNT = 5

private var maxNumber = 2

fun main() {
    val boardSize = readln().trim().toInt()
    val initialBoard = Array(boardSize) {
        readln().trim().split(" ").map(String::toInt).toIntArray()
    }
    dfs(initialBoard, 0)
    print(maxNumber)
}

private fun dfs(board: Array<IntArray>, movedCount: Int) {
    if (movedCount == LAST_MOVE_COUNT) {
        maxNumber = maxOf(maxNumber, findMaxNumber(board))
        return
    }

    dfs(getPushedUpBoard(board), movedCount + 1)
    dfs(getPushedDownBoard(board), movedCount + 1)
    dfs(getPushedLeftBoard(board), movedCount + 1)
    dfs(getPushedRightBoard(board), movedCount + 1)
}

private fun findMaxNumber(board: Array<IntArray>): Int =
    board.maxOf { columnArray ->
        columnArray.max()
    }

private fun copyBoard(board: Array<IntArray>): Array<IntArray> =
    Array(board.size) { row ->
        IntArray(board[0].size) { col ->
            board[row][col]
        }
    }

private fun getPushedUpBoard(board: Array<IntArray>): Array<IntArray> {
    val newBoard = copyBoard(board)
    mergeBoardUp(newBoard)
    pushBoardUp(newBoard)
    return newBoard
}

private fun mergeBoardUp(board: Array<IntArray>) {
    for (col in board[0].indices) {
        for (targetRow in 0 until board.lastIndex) {
            // 첫번째 숫자 찾기
            if (board[targetRow][col] == 0) {
                continue
            }

            // 두 번째 숫자 찾기
            for (nextRow in targetRow + 1 until board.size) {
                if (board[nextRow][col] != 0) {
                    if (board[nextRow][col] == board[targetRow][col]) {
                        board[targetRow][col] *= 2
                        board[nextRow][col] = 0
                    }
                    break
                }
            }
        }
    }
}

private fun pushBoardUp(board: Array<IntArray>) {
    for (column in board[0].indices) {
        for (targetRow in 0 until board.lastIndex) {
            if (board[targetRow][column] != 0) {
                continue
            }
            for (currentRow in targetRow + 1 until board.size) {
                if (board[currentRow][column] != 0) {
                    board[targetRow][column] = board[currentRow][column]
                    board[currentRow][column] = 0
                    break
                }
            }
        }
    }
}

private fun getPushedDownBoard(board: Array<IntArray>): Array<IntArray> {
    val newBoard = copyBoard(board)
    mergeBoardDown(newBoard)
    pushBoardDown(newBoard)
    return newBoard
}

private fun mergeBoardDown(board: Array<IntArray>) {
    for (col in board[0].indices) {
        for (targetRow in board.lastIndex downTo 1) {
            // 첫번째 숫자 찾기
            if (board[targetRow][col] == 0) {
                continue
            }

            // 두 번째 숫자 찾기
            for (nextRow in targetRow - 1 downTo 0) {
                if (board[nextRow][col] != 0) {
                    if (board[nextRow][col] == board[targetRow][col]) {
                        board[targetRow][col] *= 2
                        board[nextRow][col] = 0
                    }
                    break
                }
            }
        }
    }
}

private fun pushBoardDown(board: Array<IntArray>) {
    for (column in board[0].indices) {
        for (targetRow in board.lastIndex downTo 1) {
            if (board[targetRow][column] != 0) {
                continue
            }
            for (currentRow in targetRow - 1 downTo 0) {
                if (board[currentRow][column] != 0) {
                    board[targetRow][column] = board[currentRow][column]
                    board[currentRow][column] = 0
                    break
                }
            }
        }
    }
}

private fun getPushedLeftBoard(board: Array<IntArray>): Array<IntArray> {
    val newBoard = copyBoard(board)
    mergeBoardLeft(newBoard)
    pushBoardLeft(newBoard)
    return newBoard
}

private fun mergeBoardLeft(board: Array<IntArray>) {
    for (row in board.indices) {
        for (targetColumn in board[0].indices) {
            // 첫번째 숫자 찾기
            if (board[row][targetColumn] == 0) {
                continue
            }

            // 두 번째 숫자 찾기
            for (nextColumn in targetColumn + 1 until board[0].size) {
                if (board[row][nextColumn] != 0) {
                    if (board[row][nextColumn] == board[row][targetColumn]) {
                        board[row][targetColumn] *= 2
                        board[row][nextColumn] = 0
                    }
                    break
                }
            }
        }
    }
}

private fun pushBoardLeft(board: Array<IntArray>) {
    for (row in board.indices) {
        for (targetColumn in 0 until board[0].lastIndex) {
            if (board[row][targetColumn] != 0) {
                continue
            }
            for (currentColumn in targetColumn + 1 until board[0].size) {
                if (board[row][currentColumn] != 0) {
                    board[row][targetColumn] = board[row][currentColumn]
                    board[row][currentColumn] = 0
                    break
                }
            }
        }
    }
}

private fun getPushedRightBoard(board: Array<IntArray>): Array<IntArray> {
    val newBoard = copyBoard(board)
    mergeBoardRight(newBoard)
    pushBoardRight(newBoard)
    return newBoard
}

private fun mergeBoardRight(board: Array<IntArray>) {
    for (row in board.indices) {
        for (targetColumn in board[0].lastIndex downTo 1) {
            // 첫번째 숫자 찾기
            if (board[row][targetColumn] == 0) {
                continue
            }

            // 두 번째 숫자 찾기
            for (nextColumn in targetColumn - 1 downTo 0) {
                if (board[row][nextColumn] != 0) {
                    if (board[row][nextColumn] == board[row][targetColumn]) {
                        board[row][targetColumn] *= 2
                        board[row][nextColumn] = 0
                    }
                    break
                }
            }
        }
    }
}

private fun pushBoardRight(board: Array<IntArray>) {
    for (row in board.indices) {
        for (targetColumn in board[0].lastIndex downTo 1) {
            if (board[row][targetColumn] != 0) {
                continue
            }
            for (currentColumn in targetColumn - 1 downTo 0) {
                if (board[row][currentColumn] != 0) {
                    board[row][targetColumn] = board[row][currentColumn]
                    board[row][currentColumn] = 0
                    break
                }
            }
        }
    }
}