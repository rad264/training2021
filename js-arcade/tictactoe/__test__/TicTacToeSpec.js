describe("TicTacToe", function() {
    const gameArea = document.createElement("div");
    gameArea.id = "gameArea";
    document.body.appendChild(gameArea);
    let gameBoard;
    let game;
    beforeEach(function() {
        gameBoard = {
            getButton: jasmine.createSpy().and.returnValue({})
        };
        GameBoard = jasmine.createSpy().and.returnValue(gameBoard);
        alert = jasmine.createSpy();
        game = new TicTacToe();
    });
    describe("constructor", function() {
        it("creates 3x3 GameBoard in div#gameArea", function() {
            expect(GameBoard).toHaveBeenCalledWith(gameArea, 3, 3, jasmine.any(Function));
        });
        it("starts on X's turn", function() {
            expect(game.xIsNext).toBe(true);
        });
        it("starts with gameOver=false", function() {
            expect(game.gameOver).toBe(false);
        });
        it("starts with empty model", function() {
            expect(game.squares[0][0]).toBe(undefined);
            expect(game.squares[0][1]).toBe(undefined);
            expect(game.squares[0][2]).toBe(undefined);
            expect(game.squares[1][0]).toBe(undefined);
            expect(game.squares[1][1]).toBe(undefined);
            expect(game.squares[1][2]).toBe(undefined);
            expect(game.squares[2][0]).toBe(undefined);
            expect(game.squares[2][1]).toBe(undefined);
            expect(game.squares[2][2]).toBe(undefined);
        });
    });
    describe("click", function() {
        it("changes to other player's turn when empty square clicked", function() {
            const xIsNextBeforeClick = game.xIsNext;
            game.click(0, 0);
            expect(game.xIsNext).toBe(!xIsNextBeforeClick);
        });
        it("puts true in model when X's turn", function() {
            game.xIsNext = true;
            game.click(0, 0);
            expect(game.squares[0][0]).toBe(true);
        });
        it("puts false in model when O's turn", function() {
            game.xIsNext = false;
            game.click(0, 0);
            expect(game.squares[0][0]).toBe(false);
        });
        it("puts X on board when X's turn", function() {
            const buttonAt00 = {};
            gameBoard.getButton.and.returnValue(buttonAt00)
            game.xIsNext = true;
            game.click(0, 0);
            expect(buttonAt00.innerHTML).toBe("X");
        });
        it("puts O on board when O's turn", function() {
            const buttonAt00 = {};
            gameBoard.getButton.and.returnValue(buttonAt00)
            game.xIsNext = false;
            game.click(0, 0);
            expect(buttonAt00.innerHTML).toBe("O");
        });
        it("does not count as turn when square already filled", function() {
            game.xIsNext = true;
            game.squares[0][0] = false;
            game.click(0, 0);
            expect(game.xIsNext).toBe(true);
            expect(game.squares[0][0]).toBe(false);
            expect(gameBoard.getButton).not.toHaveBeenCalled();
        });
        it("sets gameOver=true when game is over", function() {
            game.isGameOver = jasmine.createSpy().and.returnValue(true);
            game.click(0, 0);
            expect(game.gameOver).toBe(true);
        });
        it("alerts win for X if game over on X's turn", function() {
            game.isGameOver = jasmine.createSpy().and.returnValue(true);
            game.xIsNext = true;
            game.click(0, 0);
            expect(alert).toHaveBeenCalledWith("X wins!");
        });
        it("alerts win for O if game over on O's turn", function() {
            game.isGameOver = jasmine.createSpy().and.returnValue(true);
            game.xIsNext = false;
            game.click(0, 0);
            expect(alert).toHaveBeenCalledWith("O wins!");
        });
        it("does not fill square when game is over", function() {
            game.gameOver = true;
            game.click(0, 0);
            expect(game.squares[0][0]).toBe(undefined);
            expect(gameBoard.getButton).not.toHaveBeenCalled();
        });
    });
    describe("isGameOver", function() {
        it("does not end game with no tic tac toe", function () {
            expect(game.isGameOver(0,0)).toBe(false)
        })
        it("ends game with a row of x's ", function () {
            game.squares[0][0] = true
            game.squares[0][1] = true
            game.squares[0][2] = true

            expect(game.isGameOver(0,3)).toBe(true)
        })
        it("ends game with a column of x's ", function () {
            game.squares[0][0] = true
            game.squares[1][0] = true
            game.squares[2][0] = true

            expect(game.isGameOver(0,0)).toBe(true)
        })
        it("ends game with a diagonal of x's ", function () {
            game.squares[0][0] = true
            game.squares[1][1] = true
            game.squares[2][2] = true

            expect(game.isGameOver(0,0)).toBe(true)
        })
        it("ends game with a cross-diagonal of x's ", function () {
            game.squares[0][2] = true
            game.squares[1][1] = true
            game.squares[2][0] = true

            expect(game.isGameOver(2,0)).toBe(true)
        })
    });
});