function Player(main) {
    var thisObj = this;
    this.squares = [[], [], [], [], [], [], [], [], [], []];
	this.shipToPlace = ["carrier", "battleship", "cruiser", "submarine", "destroyer"]
	this.count = 0;
	this.isVertical = true
    this.gameBoard = new GameBoard(document.getElementById("playerGrid"), 10, 10, function(x, y) {thisObj.click(x, y)});
	let actionButton = document.getElementById("action_button")
	actionButton.innerHTML = this.isVertical ? "Place Horizontally" : "Place Vertically"
	actionButton.onclick = () => {
		this.isVertical = !this.isVertical;
		actionButton.innerHTML = this.isVertical ? "Place Horizontally" : "Place Vertically"
	}
    this.click = function(x, y) {
		if (!main.gameStarted) {
			if (this.isVertical) {
				if (y + main.playerShips[this.shipToPlace[this.count]].size > 10) {
					alert("Cannot place ship here")
					return;	
				} 
				
				for (let i = 0; i < main.playerShips[this.shipToPlace[this.count]].size; i++){
					let button = this.gameBoard.getButton(x, y + i)
					if (button.innerHTML !== ""){
						alert("Cannot place ship here")
						return;
					}
				}
				
				for (let i = 0; i < main.playerShips[this.shipToPlace[this.count]].size; i++){
					let button = this.gameBoard.getButton(x, y + i)
					button.innerHTML = "S";
					button.id = this.shipToPlace[this.count]
					button.classList.add("ship")
				}
				this.count++
			} else {
				if (x + main.playerShips[this.shipToPlace[this.count]].size > 10) {
					alert("Cannot place ship here")
					return;	
				} 
				
				for (let i = 0; i < main.playerShips[this.shipToPlace[this.count]].size; i++){
					let button = this.gameBoard.getButton(x + i, y)
					if (button.innerHTML !== ""){
						alert("Cannot place ship here")
						return;
					}
				}
				
				for (let i = 0; i < main.playerShips[this.shipToPlace[this.count]].size; i++){
					let button = this.gameBoard.getButton(x + i, y)
					button.innerHTML = "S";
					button.id = this.shipToPlace[this.count]
					button.classList.add("ship")
				}
				this.count++
			}
			if (this.count >= this.shipToPlace.length) {
				main.gameStarted = true;
				let directions = document.getElementById("direction-text")
				directions.innerHTML = "Attack opponent by clicking on <strong>Opponent's Grid</strong>"
			} else {
				let directions = document.getElementById("direction-text")
				directions.innerHTML = `Place ships on <strong>Player's Grid</strong><br>Next ship: <strong>${this.shipToPlace[this.count].toUpperCase()} (${main.playerShips[this.shipToPlace[this.count]].size})</strong>`
			}
		}
	}

    this.isGameOver = function () {
        //TODO
		for (const ship in main.playerShips) {
			if (!main.playerShips[ship].sunk){
				return false
			} 
		}
		return true
    };
}





 