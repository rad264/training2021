const initialState = {
	prevState: null,
	loginStatus: false,
	keyPad: false,
	userId: "",
	currentAccount: {
		accountNumber: "",
		balance: 0
	},
	transfer: {
		to: "",
		from: "",
	},
	transactions: [],
	transactionIndex: 0,
	amount: "",
	alert: "",
	message: "",
	accountIndex: 0,
	accounts: [],
	content: "Main",
	menu: [
		{
			id: 'a',
			inactive: true,
			name: '',			
		},
		{
			id: 'b',
			inactive: true,
			name: '',			
		},
		{
			id: 'c',
			inactive: true,
			name: '',			
		},
		{
			id: 'd',
			inactive: true,
			name: '',			
		},
		{
			id: 'e',
			inactive: false,
			name: 'Login',			
		},
		{
			id: 'f',
			inactive: false,
			name: 'New User',			
		},
	],
}

const fullMenu = [
	{
		id: 'a',
		inactive: false,
		name: 'Balance Inquiry',			
	},
	{
		id: 'b',
		inactive: false,
		name: 'Withdraw',			
	},
	{
		id: 'c',
		inactive: false,
		name: 'Deposit',			
	},
	{
		id: 'd',
		inactive: false,
		name: 'Transfer',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'More',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Sign Out',
	},
]

const confirmMenu = [
	{
		id: 'a',
		inactive: true,
		name: '',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: false,
		name: 'Confirm',			
	},
	{
		id: 'e',
		inactive: true,
		name: '',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Cancel',
	},
]

const confirmCancelMenu = [
	{
		id: 'a',
		inactive: true,
		name: '',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: false,
		name: 'Confirm',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Cancel',
	}
]

const backMenu = [
	{
		id: 'a',
		inactive: true,
		name: '',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: true,
		name: '',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: true,
		name: '',
	},
]

const withdrawMenu = [
	{
		id: 'a',
		inactive: false,
		name: '$20',			
	},
	{
		id: 'b',
		inactive: false,
		name: '$100',			
	},
	{
		id: 'c',
		inactive: false,
		name: '$50',			
	},
	{
		id: 'd',
		inactive: false,
		name: '$200',			
	},
	{
		id: 'e',
		inactive: false,
		name: "Cancel",			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Other',
	},
]

const signoutMenu = [
	{
		id: 'a',
		inactive: true,
		name: '',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: true,
		name: '',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Home',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Sign Out',
	},
]

const transferMenu = [
	{
		id: 'a',
		inactive: false,
		name: 'To your other account',			
	},
	{
		id: 'b',
		inactive: false,
		name: 'To outside account',			
	},
	{
		id: 'c',
		inactive: false,
		name: 'From your other account',			
	},
	{
		id: 'd',
		inactive: true,
		name: '',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Cancel',
	},
]

const createAccountMenu = [
	{
		id: 'a',
		inactive: false,
		name: 'Generate Randomly',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: false,
		name: 'Confirm',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Cancel',
	},
]

const moreMenu = [
	{
		id: 'a',
		inactive: false,
		name: 'Create Account',			
	},
	{
		id: 'b',
		inactive: false,
		name: 'Transaction History',			
	},
	{
		id: 'c',
		inactive: false,
		name: 'Switch Account',			
	},
	{
		id: 'd',
		inactive: true,
		name: '',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: true,
		name: '',
	},
]

const backNextMenu = [
	{
		id: 'a',
		inactive: true,
		name: '',			
	},
	{
		id: 'b',
		inactive: true,
		name: '',			
	},
	{
		id: 'c',
		inactive: true,
		name: '',			
	},
	{
		id: 'd',
		inactive: true,
		name: '',			
	},
	{
		id: 'e',
		inactive: false,
		name: 'Back',			
	},
	{
		id: 'f',
		inactive: false,
		name: 'Next',
	},
]

const signupState = {
	content: "New User",
	menu: confirmMenu
}

const createAccountState = {
	content: "New Account",
	menu: createAccountMenu
}

const loginState = {
	content: "Login",
	menu: confirmMenu
}

const balanceState = {
	content: "Balance",
	menu: backMenu,
}

const serviceState = {
	content: "Services",
	menu: fullMenu,
}

const depositState = {
	content: "Deposit",
	menu: confirmMenu
}

const withdrawState = {
	content: "Withdraw",
	menu: withdrawMenu
}

const transferState = {
	content: "Transfer",
	menu: transferMenu
}

const transferAmountState = {
	content: "Transfer",
	menu: confirmCancelMenu
}

const otherWithdrawState = {
	content: "Withdraw",
	menu: confirmCancelMenu
}

const messageState = {
	content: "Message",
	menu: signoutMenu
}

const historyState = {
	content: "Transaction History",
	menu: backNextMenu,
}

const mapAccountsToMenu = (accounts) => {
	const newMenu = initialState.menu.map((menu, i) => {
		if (i < accounts.length){
			return {
				...menu,
				name: accounts[i],
				inactive: false
			}
		} else {
			if (i===4) {
				return {
					...menu,
					name: "Back",
					inactive: false
				}
			}
			return {
				...menu,
				name: "",
				inactive: true
			}
		}
					
	})
	return newMenu
}

function CoreButton(props) {
	
	const onClickUpdate = () => {
		if (props.name === "Login") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: loginState.content,
				menu: loginState.menu,
				keyPad: false,
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Sign Out") {
			return props.onClick(initialState)
		} else if (props.name === "Home") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: serviceState.content,
				menu: serviceState.menu,
				keyPad: false,
				prevState: prevState,
				message: "Select your transaction",
			}
			return props.onClick(props.state)
		} else if (props.name === "New User"){
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: signupState.content,
				menu: signupState.menu,
				keyPad: false,
				userId: "",
				prevState: prevState,
			}
			return props.onClick(props.state)
		}
		
		else if (props.name === "Confirm"){
			if (props.state.content === "Login"){
				let prevState = {...props.state}
				login(props.state.userId).then((response) => {
					if (response.accounts){
						let newMenu;
						if (response.accounts.length > (props.state.accountIndex + 4)){
							newMenu = mapAccountsToMenu(response.accounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
							newMenu[5] = {
								...newMenu[5],
								name: "Next",
								inactive: false
							}
						} else {
							newMenu = mapAccountsToMenu(response.accounts.slice(props.state.accountIndex))
						}
						
						props.state = {
							...props.state,
							alert: "",
							loginStatus: true,
							accounts: response.accounts,
							content: "Accounts",
							menu: newMenu,
							keyPad: false,
							prevState: prevState,
						}	
					} else {
						props.state = {
							...props.state,
							alert: "",
							loginStatus: true,
							accounts: response.accounts,
							content: createAccountState.content,
							menu: createAccountState.menu,
							message: "Create new account",
							keyPad: true,
							prevState: prevState,
						}	
					}
					return props.onClick(props.state)
				}).catch(e => {
					let alert;
					if (props.state.userId){
						alert = e.status === 404 ? "User Not Found" : "Unexpected Error";
					} else {
						alert = "User ID cannot be empty"
					}
					props.state = {
						...props.state,
						alert: alert,
					}
					return props.onClick(props.state)
				})				
			} else if (props.state.content === "Deposit"){
				let alert;
				if (props.state.amount.length === 0) {
					alert = "Invalid Amount"
					props.state = {
						...props.state,
						alert: alert,
					}
					return props.onClick(props.state)
				}
				console.log(Number(props.state.amount))
				deposit(props.state.currentAccount.accountNumber, props.state.amount).then((response) => {
					let prevState = {...props.state}
					let message = "Deposit Successful!"
					props.state = {
						...props.state,
						content: messageState.content,
						menu: messageState.menu,
						message: message,
						alert: "",
						amount: "",
						keyPad: false,
						prevState: prevState,
					}
					return props.onClick(props.state)
				}).catch(e => {
					if (props.state.amount.length === 0) {
						alert = "Invalid Amount"
					} else if (isNaN(props.state.amount)){
						alert = "Invalid Amount"
					} else{
						alert = e.status === 404 ? "Account Not Found" : "Unexpected Error";
					}
					props.state = {
						...props.state,
						alert: alert
					}
					return props.onClick(props.state)
				})
			} else if (props.state.content === "Withdraw") {
				let prevState = {...props.state}
				withdraw(props.state.currentAccount.accountNumber, Number(props.state.amount)).then((response) => {
					let message = "Withdraw Successful!"
					props.state = {
						...props.state,
						content: messageState.content,
						menu: messageState.menu,
						message: message,
						alert: "",
						amount: "",
						keyPad: false,
						prevState: prevState,
					}
					return props.onClick(props.state)
				}).catch(e => {
					let alert = e.status === 404 ? "Account Not Found" : "Unexpected Error";
					props.state = {
						...props.state,
						alert: alert
					}
					return props.onClick(props.state)
				})
			} else if (props.state.content === "New User") {
				let prevState = {...props.state}
				createUser(props.state.userId).then((response) => {
					props.state = {
						...props.state,
						loginStatus: true,
						content: createAccountState.content,
						menu: createAccountState.menu,
						userId: response.userId,
						message: "",
						alert: "",
						keyPad: true,
						prevState: prevState,
					}
					return props.onClick(props.state)
				}).catch(e => {
					let alert = e.status === 409 ? "User Id already exists" : "Unexpected Error";
					props.state = {
						...props.state,
						alert: alert
					}
					return props.onClick(props.state)
				})
			} else if (props.state.content === "New Account") {
				let prevState = {...props.state}
				if (props.state.amount.length != 6) {
					let alert = "Account number must be 6-digits";
					props.state = {
						...props.state,
						alert: alert
					}
					return props.onClick(props.state)
				}
				
				createAccount(props.state.userId, props.state.amount).then((response) => {
					props.state.accounts.push(response.accountNumber)
					props.state = {
						...props.state,
						currentAccount: response,
						content: serviceState.content,
						menu: serviceState.menu,
						alert: "",
						message: "Select your transaction",
						keyPad: false,
						prevState: prevState,
					}
					return props.onClick(props.state)
				}).catch(e => {
					let alert = e.status === 409 ? "Account number already exists" : "Unexpected Error";
					props.state = {
						...props.state,
						alert: alert
					}
					return props.onClick(props.state)
				})
			} else if (props.state.content === "Transfer") {
				let prevState = {...props.state}
				if (props.state.message === "Please review your transfer") {
					transfer(props.state.transfer.from, props.state.transfer.to, Number(props.state.amount)).then((response) => {
						let message = "Transfer Successful!"
						props.state = {
							...props.state,
							content: messageState.content,
							menu: messageState.menu,
							message: message,
							alert: "",
							amount: "",
							keyPad: false,
							prevState: prevState,
						}
						return props.onClick(props.state)
					}).catch(e => {
						let alert;
						switch (e.status){
							case 404:
								alert = "Account Not Found"
								break
							case 406:
								alert = "Invalid amount"
								break
							default:
								alert = "Unexpected Error"
						}
						props.state = {
							...props.state,
							alert: alert
						}
						return props.onClick(props.state)
					})
				} else if (props.state.message === "Enter the account you want to transfer to") {
					let prevState = {...props.state}
					let toAccount = props.state.amount
					props.state = {
						...props.state,
						transfer: {
							...props.state.transfer,
							to: toAccount
						},
						amount: "",
						content: transferAmountState.content,
						menu: transferAmountState.menu,
						keyPad: true,
						message: "Enter an amount to transfer",
						prevState: prevState,
					}
					return props.onClick(props.state)
				}
				props.state = {
					...props.state,
					keyPad: false,
					message: "Please review your transfer",
					alert: "",
					menu: confirmCancelMenu,
					prevState: prevState,
				}
				return props.onClick(props.state)
			}
		} else if (props.name === "Balance Inquiry") {
			let prevState = {...props.state}
			checkBalance(props.state.currentAccount.accountNumber).then((response) => {	
				console.log(response)			
				props.state = {
					...props.state,
					content: balanceState.content,
					menu: balanceState.menu,
					currentAccount: response,
					keyPad: false,
					prevState: prevState,
				}
				return props.onClick(props.state)
			}) .catch(e => {
				let alert = e.status === 404 ? "Account Not Found" : "Unexpected Error";
				props.state = {
					...props.state,
					alert: alert
				}
				return props.onClick(props.state)
			})
		} else if (props.name === "Cancel") {
			if (["Balance", "Deposit", "Transfer", "New Account"].includes(props.state.content)){
				props.state = {
					...props.state,
					content: serviceState.content,
					menu: serviceState.menu,
					alert: "",
					message: "Select your transaction",
					amount: "",
					keyPad: false
				}
				return props.onClick(props.state)
			} else if (props.state.content === "Withdraw") {
				if (props.name === "Back") {
					props.state = {
						...props.state,
						content: withdrawState.content,
						menu: withdrawState.menu,
						alert: "",
						message: "Select an amount to withdraw",
						amount: "",
						keyPad: false
					}
					return props.onClick(props.state)
				} else {
					props.state = {
						...props.state,
						content: serviceState.content,
						menu: serviceState.menu,
						alert: "",
						message: "Select your transaction",
						amount: "",
						keyPad: false
					}
					return props.onClick(props.state)
				}
			} else if (["Login", "Services", "New User"].includes(props.state.content)){
				return props.onClick(initialState)
			}
		} else if (props.name === "Back") {
			return props.onClick(props.state.prevState)
		} else if (props.name === "Deposit") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: depositState.content,
				menu: depositState.menu,
				keyPad: true,
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Withdraw") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: withdrawState.content,
				menu: withdrawState.menu,
				message: "Select an amount to withdraw",
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Transfer") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: transferState.content,
				menu: transferState.menu,
				message: "Choose your transfer option",
				alert: "",
				amount: "",
				keyPad: false,
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Create Account"){
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: createAccountState.content,
				menu: createAccountState.menu,
				message: "",
				alert: "",
				amount: "",
				keyPad: true,
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Generate Randomly"){
			let random = Math.floor(100000 + Math.random() * 900000)
			props.state = {
				...props.state,
				amount: random.toString()
			}
			let input = document.querySelector('input');
			return props.onClick(props.state)
		} else if (props.name === "To your other account"){
			let prevState = {...props.state}
			let otherAccounts = props.state.accounts.filter(acc => acc !== props.state.currentAccount.accountNumber)
			let newMenu;
			if (otherAccounts.length > (props.state.accountIndex + 4)){
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
				newMenu[5] = {
					...newMenu[5],
					name: "Next",
					inactive: false
				}
			} else {
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex))
			}
			props.state = {
				...props.state,
				menu: newMenu,
				transfer: {
					...props.state.transfer,
					from: props.state.currentAccount.accountNumber,
				},
				message: "Choose the account you want to transfer to",
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "To outside account") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				transfer: {
					...props.state.transfer,
					from: props.state.currentAccount.accountNumber,
				},
				content: transferAmountState.content,
				menu: transferAmountState.menu,
				keyPad: true,
				message: "Enter the account you want to transfer to",
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "From your other account"){
			let prevState = {...props.state}
			let otherAccounts = props.state.accounts.filter(acc => acc !== props.state.currentAccount.accountNumber)
			let newMenu;
			if (otherAccounts.length > (props.state.accountIndex + 4)){
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
				newMenu[5] = {
					...newMenu[5],
					name: "Next",
					inactive: false
				}
			} else {
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex))
			}
			props.state = {
				...props.state,
				menu: newMenu,
				transfer: {
					...props.state.transfer,
					to: props.state.currentAccount.accountNumber,
				},
				message: "Choose the account you want to transfer from",
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "More") {
			let prevState = {...props.state}
			props.state = {
				...props.state,
				content: serviceState.content,
				message: "Select your transaction",
				menu: moreMenu,
				prevState: prevState,
			}
			return props.onClick(props.state)
		} else if (props.name === "Switch Account") {
			let prevState = {...props.state}
			let otherAccounts = props.state.accounts.filter(acc => acc !== props.state.currentAccount.accountNumber)
			let newMenu;
			if (otherAccounts.length > (props.state.accountIndex + 4)){
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
				newMenu[5] = {
					...newMenu[5],
					name: "Next",
					inactive: false
				}
			} else {
				newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex))
			}
			props.state = {
				...props.state,
				menu: newMenu,
				message: "Choose the account you want to switch to",
				prevState: prevState,
			}
			return props.onClick(props.state)
			
		} else if (props.name === "Next") {
			if (props.state.content === "Accounts"){
				let prevState = {...props.state}
				let newMenu;
				props.state.accountIndex += 4
				if (props.state.accounts.length > (props.state.accountIndex + 4)){
					newMenu = mapAccountsToMenu(props.state.accounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
					newMenu[5] = {
						...newMenu[5],
						name: "Next",
						inactive: false
					}
				} else {
					newMenu = mapAccountsToMenu(props.state.accounts.slice(props.state.accountIndex))
				}
				props.state = {
					...props.state,
					menu: newMenu,
					prevState: prevState
				}
				return props.onClick(props.state)
			} else if (props.state.content === "Transaction History") {
				let prevState = {...props.state}
				props.state.transactionIndex += 10;
				let newMenu = backMenu
				if (props.state.transactions.length > props.state.transactionIndex + 10) {
					newMenu = backNextMenu
				}
				props.state = {
					...props.state,
					menu: newMenu,
					prevState: prevState
				}
				return props.onClick(props.state)
			} else {
				let prevState = {...props.state}
				let newMenu;
				props.state.accountIndex += 4
				let otherAccounts = props.state.accounts.filter(acc => acc !== props.state.currentAccount.accountNumber)
				if (otherAccounts.length > (props.state.accountIndex + 4)){
					newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex, props.state.accountIndex + 4))
					newMenu[5] = {
						...newMenu[5],
						name: "Next",
						inactive: false
					}
				} else {
					newMenu = mapAccountsToMenu(otherAccounts.slice(props.state.accountIndex))
				}
				props.state = {
					...props.state,
					menu: newMenu,
					prevState: prevState
				}
				return props.onClick(props.state)
			}
		} else if (props.name === "Transaction History") {
			let prevState = {...props.state}
			getAllTransactionsByAccount(props.state.currentAccount.accountNumber).then((response) => {
				let newMenu = historyState.menu
				if (response.length < 10) {
					newMenu = backMenu
				}
				props.state = {
					...props.state,
					menu: newMenu,
					content: historyState.content,
					transactions: response,
					transactionIndex: 0,
					message: "",
					alert: "",
					amount: "",
					keyPad: false,
					prevState: prevState,
				}
				return props.onClick(props.state)
			}).catch(e => {
				let alert = e.status === 404 ? "Account Not Found" : "Unexpected Error";
				props.state = {
					...props.state,
					alert: alert
				}
				return props.onClick(props.state)
			})
		}
		
		
		else {
			if (props.state.content === "Accounts" || props.state.content === "Services"){
				let prevState = {...props.state}
				
				props.state = {
					...props.state,
					currentAccount: {
						accountNumber: props.name,
						balance: 0,
					},
					accountIndex: 0,
					content: serviceState.content,
					menu: serviceState.menu,
					prevState: prevState,
					message: "Select your transaction"
				}
				return props.onClick(props.state)
			}
			if (props.state.content === "Withdraw"){
				let prevState = {...props.state}
				if (props.name === "Other"){
					props.state = {
						...props.state,
						menu: otherWithdrawState.menu,
						message: "",
						keyPad: true,
						prevState: prevState,
					}
					return props.onClick(props.state)
				} else {
					props.state = {
						...props.state,
						menu: otherWithdrawState.menu,
						message: "Confirm your deposit amount",
						amount: props.name.substring(1),
						prevState: prevState,
					}
					return props.onClick(props.state)
				}
				
			}
			if (props.state.content === "Transfer") {
				let prevState = {...props.state}
				if (props.state.message.includes("from")) {
					let fromAccount = props.name
					props.state = {
						...props.state,
						transfer: {
							...props.state.transfer,
							from: fromAccount,
						},
						content: transferAmountState.content,
						menu: transferAmountState.menu,
						keyPad: true,
						accountIndex: 0,
						message: "Enter an amount to transfer",
						prevState: prevState,
					}
					return props.onClick(props.state)
				}
				let toAccount = props.name
				props.state = {
					...props.state,
					transfer: {
						...props.state.transfer,
						to: toAccount,
					},
					content: transferAmountState.content,
					menu: transferAmountState.menu,
					keyPad: true,
					accountIndex: 0,
					message: "Enter an amount to transfer",
					prevState: prevState,
				}
				return props.onClick(props.state)
			}
		}
	}
	
	return (
		<div className="buttonWrapper" id={props.direction}>
			<button onClick={onClickUpdate} disabled={props.inactive}></button>
		</div>
	);
}