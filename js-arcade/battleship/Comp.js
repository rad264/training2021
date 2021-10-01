function Comp(main) {
    var thisObj = this;
	this.prev = {
		x: null,
		y: null,
		hit: false,
		count: 0,
		missCount: 0,
		firstHit: {
			x: null,
			y: null,
		},
		direction: ""
	}
	this.directions = ["up", "down", "left", "right"]
	this.hit = 0;
    this.squares = [[], [], [], [], [], [], [], [], [], []];
    this.gameBoard = new GameBoard(document.getElementById("compGrid"), 10, 10, function(x, y) {thisObj.click(x, y)});
	let notifications = document.getElementById('messages')
	let playerBoard = main.player.gameBoard;
	placeShips(this.gameBoard);
    this.click = function(x, y) {
		if (!main.gameStarted) {
			alert("Place your ships first!")
			return
		} else {
			if (!main.gameOver) {
				var button = this.gameBoard.getButton(x, y);
				if (button.id === ""){
					button.innerHTML = "X";
					button.id = "miss"
					thisObj.attack(playerBoard)
				} else {
					if (button.innerHTML === "") {
						let hitTarget = button.id
						button.innerHTML = "O"
						button.id = "hit"
						main.compShips[hitTarget].hp--
						if (main.compShips[hitTarget].hp === 0){
							main.compShips[hitTarget].sunk = true
							notifications.innerHTML += `<div class="notification-text player-text"><strong style="color: blue;">You</strong> sank ${hitTarget.toUpperCase()}</div>`
						}
						thisObj.attack(playerBoard)
					}
				}
				if (this.allCompShipSank()){
					main.gameOver = true;
					alert("You won!")
					let directions = document.getElementById("direction-text")
					directions.innerHTML = "Press <strong>New Game</strong> to play again."
				}
						
				main.playerIsNext = false;
				
				if (this.allPlayerShipSank()){
					main.gameOver = true;
					alert("All your ships sank! Computer Won!")
					let directions = document.getElementById("direction-text")
					directions.innerHTML = "Press <strong>New Game</strong> to play again."
				}
			}
		}
    };

    this.allCompShipSank = function () {
        //TODO
		for (const ship in main.compShips) {
			if (!main.compShips[ship].sunk){
				return false
			} 
		}
		return true
    };

	this.allPlayerShipSank = function () {
		for (const ship in main.playerShips) {
			if (!main.playerShips[ship].sunk) {
				return false
			}
		}
		return true
	}

	this.attack = function(playerBoard) {
		
		if (!this.prev.hit){
			let keepGoing = true
			let button;
			let newX;
			let newY;
			while (keepGoing){
				newX = Math.floor(Math.random() * 10)
				newY = Math.floor(Math.random() * 10)
				button = playerBoard.getButton(newX, newY)
				if (button.innerHTML !== "O" && button.innerHTML !== "X") {
					keepGoing = false
				}
			}
			
			if (button.innerHTML === "S") {
				button.innerHTML = "O"
				let hitTarget = button.id
				main.playerShips[hitTarget].hp--
				if (main.playerShips[hitTarget].hp === 0){
					main.playerShips[hitTarget].sunk = true
					notifications.innerHTML += `<div class="notification-text computer-text"><strong style="color: red;">Computer</strong> sank ${hitTarget.toUpperCase()}</div>`
					this.prev = {
						...this.prev,
						x: null,
						y: null,
						firstHit: {
							x: null,
							y: null,
						},
						count: 0,
						hit: false,
						missCount: 0,
						direction: ""
					}
					this.hit = 0
				} else {
					let direction = excludeAndSelectRandomly(newX, newY, this.directions, [])
					this.prev = {
						...this.prev,
						x: newX,
						y: newY,
						firstHit: {
							x: newX,
							y: newY,
						},
						hit: true,
						count: this.hit,
						missCount: 0,
						direction: direction
					}
					this.hit ++;
				}
				button.id = "hit"
				
			} else {
				button.innerHTML = "X"
				button.id = "miss"
				this.prev = {
					...this.prev,
					x: newX,
					y: newY,
					hit: false,
					count: 0,
				}
			}
		} else {
			if (this.prev.count === 0) {
				let newDirection = this.prev.direction
				let newPosition = addOneToDirection(this.prev.x, this.prev.y, newDirection)
				let button = playerBoard.getButton(newPosition.x, newPosition.y)
				let keepGoing = true
				while (keepGoing) {
					if (button.innerHTML !== "O" && button.innerHTML !== "X") {
						keepGoing = false
						break;
					}
					newDirection = excludeAndSelectRandomly(this.prev.x, this.prev.y, this.directions, [])
					newPosition = addOneToDirection(this.prev.x, this.prev.y, newDirection)
					button = playerBoard.getButton(newPosition.x, newPosition.y)
				}
				if (button.innerHTML === "S") {
					button.innerHTML = "O"
					let hitTarget = button.id
					main.playerShips[hitTarget].hp--
					if (main.playerShips[hitTarget].hp === 0){
						main.playerShips[hitTarget].sunk = true
						notifications.innerHTML += `<div class="notification-text computer-text"><strong style="color: red;">Computer</strong> sank ${hitTarget.toUpperCase()}</div>`
						this.prev = {
							...this.prev,
							x: null,
							y: null,
							count: 0,
							hit: false,
							missCount: 0,
							direction: ""
						}
						this.hit = 0
					} else {
						this.prev = {
							...this.prev,
							x: newPosition.x,
							y: newPosition.y,
							hit: true,
							count: this.hit,
							missCount: 0,
							direction: newDirection
						}
						this.hit ++
					}
					button.id = "hit"
					
				} else {
					button.innerHTML = "X"
					button.id = "miss"
					newX = this.prev.x
					newY = this.prev.y
					let direction = excludeAndSelectRandomly(newX, newY, this.directions, [this.prev.direction])
					this.prev = {
						...this.prev,
						x: newX,
						y: newY,
						direction: direction
					}
				}
			} else {
				let newDirection = this.prev.direction
				let newPosition = addOneToDirection(this.prev.x, this.prev.y, newDirection)
				let button = playerBoard.getButton(newPosition.x, newPosition.y)
				if (button.innerHTML === "O" && button.innerHTML === "X") {
					switch (newDirection) {
						case "up":
							newDirection = 'down'
							newPosition.y += (this.prev.count + 1)
							break;
						case "down":
							newDirection = 'up'
							newPosition.y -= (this.prev.count + 1)
							break;
						case "right":
							newDirection = 'left'
							newPosition.x -= (this.prev.count + 1)
							break;
						default:
							newDirection = 'right'
							newPosition.x += (this.prev.count + 1)
					}
					newPosition = addOneToDirection(newPosition.x, newPosition.y, newDirection)
					button = playerBoard.getButton(newPosition.x, newPosition.y)
				}
				if (button.innerHTML === "S") {
					button.innerHTML = "O"
					let hitTarget = button.id
					main.playerShips[hitTarget].hp--
					if (main.playerShips[hitTarget].hp === 0){
						main.playerShips[hitTarget].sunk = true
						notifications.innerHTML += `<div class="notification-text computer-text"><strong style="color: red;">Computer</strong> sank ${hitTarget.toUpperCase()}</div>`
						this.prev = {
							...this.prev,
							x: null,
							y: null,
							count: 0,
							hit: false,
							missCount: 0,
							direction: ""
						}
						this.hit = 0
					} else {
						this.prev = {
							...this.prev,
							x: newPosition.x,
							y: newPosition.y,
							hit: true,
							count: this.hit,
							missCount: 0,
							direction: newDirection
						}
						this.hit ++
					}
					button.id = "hit"
					
				} else if (button.innerHTML === "O" || button.innerHTML === "X") {
					let newDirection = excludeAndSelectRandomly(this.prev.firstHit.x, this.prev.firstHit.y, this.directions, [this.prev.direction])
					let newPosition = addOneToDirection(this.prev.firstHit.x, this.prev.firstHit.y, newDirection)
					let button = playerBoard.getButton(newPosition.x, newPosition.y)
					let keepGoing = true
					let count = 0
					while (keepGoing) {
						if (count > 10) {
							let newX = Math.floor(Math.random() * 10)
							let newY = Math.floor(Math.random() * 10)
							button = playerBoard.getButton(newX, newY)
						}
						if (button.innerHTML !== "O" && button.innerHTML !== "X") {
							keepGoing = false
							break;
						}
						newDirection = excludeAndSelectRandomly(this.prev.firstHit.x, this.prev.firstHit.y, this.directions, [])
						newPosition = addOneToDirection(this.prev.firstHit.x, this.prev.firstHit.y, newDirection)
						button = playerBoard.getButton(newPosition.x, newPosition.y)
						count++
					}
					if (button.innerHTML === "S") {
						button.innerHTML = "O"
						let hitTarget = button.id
						main.playerShips[hitTarget].hp--
						if (main.playerShips[hitTarget].hp === 0){
							main.playerShips[hitTarget].sunk = true
							notifications.innerHTML += `<div class="notification-text computer-text"><strong style="color: red;">Computer</strong> sank ${hitTarget.toUpperCase()}</div>`
							this.prev = {
								...this.prev,
								x: null,
								y: null,
								count: 0,
								hit: false,
								missCount: 0,
								direction: ""
							}
							this.hit = 0
						} else {
							this.prev = {
								...this.prev,
								x: newPosition.x,
								y: newPosition.y,
								hit: true,
								count: this.hit,
								missCount: 0,
								direction: newDirection
							}
							this.hit ++
						}
						button.id = "hit"
						
					} else {
						button.innerHTML = "X"
						button.id = "miss"
						newX = this.prev.x
						newY = this.prev.y
						let direction = excludeAndSelectRandomly(newX, newY, this.directions, [this.prev.direction])
						this.prev = {
							...this.prev,
							x: newX,
							y: newY,
							direction: direction
						}
					}
				} else 	{
					if (this.prev.missCount < 2) {
						button.innerHTML = "X"
						button.id = "miss"
						switch (newDirection) {
							case "up":
								newDirection = 'down'
								newPosition.y += (this.prev.count + 1)
								break;
							case "down":
								newDirection = 'up'
								newPosition.y -= (this.prev.count + 1)
								break;
							case "right":
								newDirection = 'left'
								newPosition.x -= (this.prev.count + 1)
								break;
							default:
								newDirection = 'right'
								newPosition.x += (this.prev.count + 1)
						}
						this.prev = {
							...this.prev,
							x: newPosition.x,
							y: newPosition.y,
							direction: newDirection,
							missCount: this.prev.missCount + 1
						}
					}
				}
			}
		}
	}
}

function placeShips(gameBoard) {
	for (const ship in compShips) {
		let vertical = Math.random() < 0.5
		if (vertical) {
			let notPlaced = true
			while (notPlaced) {
				let randomX = Math.floor(Math.random() * 10)
				let randomY = Math.floor(Math.random() * 10)
				notPlaced = !placeShipVertically(gameBoard, ship, randomX, randomY)
			}
		} else {
			let notPlaced = true
			while (notPlaced) {
				let randomX = Math.floor(Math.random() * 10)
				let randomY = Math.floor(Math.random() * 10)
				notPlaced = !placeShipHorizontally(gameBoard, ship, randomX, randomY)			
			}
		}
	}	
}

function placeShipVertically(gameBoard, ship, x, y) {
	let start = y
	if (y + compShips[ship].size > 10) {
		start = 10 - compShips[ship].size	
	}
	
	for (let i = 0; i < compShips[ship].size; i++){
		let button = gameBoard.getButton(x, start + i)
		if (button.id !== "") {
			return false
		}
	}
	
	for (let i = 0; i < compShips[ship].size; i++){
		let button = gameBoard.getButton(x, start + i)
		button.id = ship
	}
	return true
}

function placeShipHorizontally(gameBoard, ship, x, y) {
	let start = x
	if (x + compShips[ship].size > 10) {
		start = 10 - compShips[ship].size	
	}
	
	for (let i = 0; i < compShips[ship].size; i++){
		let button = gameBoard.getButton(start + i, y)
		if (button.id !== "") {
			return false
		}
	}
	
	for (let i = 0; i < compShips[ship].size; i++){
		let button = gameBoard.getButton(start + i, y)
		button.id = ship
	}
	return true
}

function excludeAndSelectRandomly(x, y, array, excludes) {
	if (x === 0) {
		excludes.push("left")
	}
	if (x === 9) {
		excludes.push("right")
	}
	if (y === 0) {
		excludes.push("up")
	}
	if (y === 9) {
		excludes.push("down")
	}
	
	const newArray = array.filter(element => !excludes.includes(element))
	return newArray[Math.floor(Math.random() * newArray.length)]
}

function addOneToDirection(x, y, direction) {
	switch (direction) {
		case "up":
			y --
			break;
		case "down":
			y ++
			break;
		case "right":
			x ++
			break
		default:
			x --
	}
	return {x, y}
}





 