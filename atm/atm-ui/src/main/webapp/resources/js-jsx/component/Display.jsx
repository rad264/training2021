function Display(props) {
	
	return (
		<div>
			<div id="bezel">
				<div id="screen">
					<div id="header">
						{props.state.loginStatus && <div>User ID: {props.state.userId}</div>}
						{props.state.currentAccount.accountNumber && <div>Account #: {props.state.currentAccount.accountNumber}</div>}
					</div>
					<div id="main">
						{props.state.content === "New User" && <NewUserForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "New Account" && <CreateAccountForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "Login" && <LoginForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "Balance" && <div className="header">Current Balance: {'\n'} ${props.state.currentAccount.balance.toFixed(2)}</div>}
						{props.state.content === "Accounts" && <div className="header">Select your account</div>}
						{props.state.content === "Services" && <div className="header">{props.state.message}</div>}
						{props.state.content === "Deposit" && <DepositForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "Withdraw" && <WithdrawForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "Transfer" && <TransferForm state={props.state} updateState={props.updateState} />}
						{props.state.content === "Transaction History" && <TransactionHistory state={props.state}/>}
						{props.state.content === "Main" && <div className="header">Welcome<span className="blink"></span></div>}
						{props.state.content === "Message" && <Message message={props.state.message}/>}
						{props.state.alert && <Alert alert={props.state.alert}/>}
					</div>
					{<Menu menu={props.state.menu}/>}
				</div>
			</div>
		</div>
	)
}