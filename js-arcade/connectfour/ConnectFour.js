function ConnectFour() {
    var thisObj = this;
    this.squares = [[], [], [], [], [], [], []];
    this.xIsNext = true;
    this.gameOver = false;
    var gameBoard = new GameBoard(document.getElementById("gameArea"), 7, 6, function(x, y) {thisObj.click(x, y)});
    this.click = function(x, y) {
		if (!this.gameOver) {
			let row = 5
			while (this.squares[x][row] !== undefined) {
				row--
			}
			var button = gameBoard.getButton(x, row);
			button.innerHTML = this.xIsNext ? "X" : "O";
			this.squares[x][row] = this.xIsNext;
			if (this.isGameOver(x, row)){
				this.gameOver = true;
				alert((this.xIsNext ? "X" : "O") + " wins!")
			}
			this.xIsNext = !this.xIsNext;
		}
    };
    this.isGameOver = function (x, y) {
        //TODO
		let mark = gameBoard.getButton(x,y).innerHTML
		
		return this.checkRow(x, y, mark) || this.checkCol(x, y, mark) || this.checkDiag(x, y, mark)
    };

	this.checkRow = function (x, y, mark) {
		let count = 0
	
		for (let i = -3; i < 4; i++){
			if (y + i >= 0 && y + i <= 5){
				let button = gameBoard.getButton(x, y + i)
				if (button.innerHTML !== mark){
					count = 0
				} else {
					count++
				}
				if (count == 4) return true
			}
		}
		return count == 4
	}
	
	this.checkCol = function (x, y, mark) {
		let count = 0
	
		for (let i = -3; i < 4; i++){
			if (x + i >= 0 && x + i <= 6){
				let button = gameBoard.getButton(x + i, y)
			
				if (button.innerHTML !== mark){
					count = 0
				} else {
					count++
				}
				if (count == 4) return true
			}
		}
		return count == 4
	}
	
	this.checkDiag = function (x, y, mark) {
		let count = 0
	
		for (let i = -3; i < 4; i++){
			if (x + i >= 0 && x + i <= 6 && y - i >= 0 && y - i <= 5){
				let button = gameBoard.getButton(x + i, y - i)
			
				if (button.innerHTML !== mark){
					count = 0
				} else {
					count++
				}
				if (count == 4) return true
			}
		}
		for (let i = -3; i < 4; i++){
			if (x + i >= 0 && x + i <= 6 && y + i >= 0 && y + i <= 5){
				let button = gameBoard.getButton(x + i, y + i)
			
				if (button.innerHTML !== mark){
					count = 0
				} else {
					count++
				}
				if (count == 4) return true
			}
		}
		return count == 4
	}
}     