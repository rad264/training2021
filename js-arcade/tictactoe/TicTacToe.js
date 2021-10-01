function TicTacToe() {
    var thisObj = this;
    this.squares = [[], [], []];
    this.xIsNext = true;
    this.gameOver = false;
    var gameBoard = new GameBoard(document.getElementById("gameArea"), 3, 3, function(x, y) {thisObj.click(x, y)});
    this.click = function(x, y) {
        if (!this.gameOver && this.squares[x][y] === undefined) {
            var button = gameBoard.getButton(x, y);
            button.innerHTML = this.xIsNext ? "X" : "O";
            this.squares[x][y] = this.xIsNext;
            if (this.isGameOver(x, y)) {
                this.gameOver = true;
                alert((this.xIsNext ? "X" : "O") + " wins!");
            }
            this.xIsNext = !this.xIsNext;
        }
    };
    this.isGameOver = function (x, y) {
        //TODO
				let mark = gameBoard.getButton(x,y).innerHTML
		
		return this.checkRow(x, mark) || this.checkCol(y, mark) || this.checkDiag(mark)
    };

	this.checkRow = function (x, mark) {
		let win = true;
	
		for (let y = 0; y < 3; y++){
			let button = gameBoard.getButton(x, y)
			
			if (button.innerHTML !== mark){
				win = false
				return win
			}
		}
		return win
	}
	
	this.checkCol = function (y, mark) {
		let win = true;
	
		for (let x = 0; x < 3; x++){
			let button = gameBoard.getButton(x, y)
			
			if (button.innerHTML !== mark){
				win = false
				return win
			}
		}
		return win
	}
	
	this.checkDiag = function (mark) {
		let win = true;
		for (let i = 0; i < 3; i ++){
			let button = gameBoard.getButton(i, i)
			
			if (button.innerHTML !== mark){
				win = false
			}
		}
		if (!win) {
			win = true
			y = 0
			for (let x = 2; x >= 0; x--){
				let button = gameBoard.getButton(x, y)
				if (button.innerHTML !== mark){
					win = false
				}
				y++
			}
		}
		
		return win 
    };
}     