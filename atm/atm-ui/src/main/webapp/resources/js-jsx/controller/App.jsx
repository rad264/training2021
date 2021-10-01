class App extends React.Component {
	
	constructor(props) {
		super(props);
		this.state = {
			loggedInUserId: null,
			loggedInUserAccounts: null,
			responseStatus: null
		}
		this.updateUserId = this.updateUserId.bind(this);
		this.updateAccounts = this.updateAccounts.bind(this);
		this.getUserId = this.getUserId.bind(this);
		this.getAccounts = this.getAccounts.bind(this);
		this.handleLogout = this.handleLogout.bind(this);
	}
	
	
	handleLogout() {
		this.setState({loggedInUserId: null, loggedInUserAccounts: null})
	}
	

	updateUserId(userId) {
		this.setState({loggedInUserId: userId})
	}
	
	updateAccounts(accounts) {
		this.setState({loggedInUserAccounts: accounts})
	}
	
	getUserId() {
		return this.state.loggedInUserId;
	}
	
	getAccounts() {
		return this.state.loggedInUserAccounts;
	}
	
	
	render() {
		let loggedInComponents = 
			<div>
				<p>User: {" " + this.state.loggedInUserId}</p>
				<LogoutController
				 	{...this.props}
					logout={this.handleLogout}
				/>
				<hr />
				<AccountsSummaryController 
					{...this.props} 
					getUser={this.getUserId}
				/>
				<br />
				<hr />
				<NewAccountController 
					{...this.props}
					updateAccounts={this.updateAccounts}
					getUser={this.getUserId} 
					getAccounts={this.getAccounts}
				/>
				<br />
				<hr />
				<CheckBalanceController 
					{...this.props}
					getAccounts={this.getAccounts} 
				/>
				<br />
				<hr />
				<DepositController 
					{...this.props}
					getAccounts={this.getAccounts} 
				/>
				<br />
				<hr />
				<WithdrawController 
					{...this.props}
					getAccounts={this.getAccounts} 
				/>
				<br />
				<hr />
				<TransferController 
					{...this.props}
					getAccounts={this.getAccounts} 
				/>
				<br />
			</div>
				
		let notLoggedInComponents =
			<div>
				<LoginController 
					{...this.props} 
					updateUser={this.updateUserId} 
					updateAccounts={this.updateAccounts} 
				/>
				<br />
				<hr />
				<NewUserController
					{...this.props} 
					updateUser={this.updateUserId} 
					updateAccounts={this.updateAccounts}
				/>
			</div> 
			
		let newUserComponents =
			<div>
				<p>User: {" " + this.state.loggedInUserId}</p>
				<LogoutController
				 	{...this.props}
					logout={this.handleLogout}
				/>
				<hr />
				<NewAccountController 
					{...this.props}
					updateAccounts={this.updateAccounts}
					getUser={this.getUserId} 
					getAccounts={this.getAccounts}
				/>
				</div>
		
		return(
			<div>
				<h1 className="atmHeader">ATM</h1>
				{(this.state.loggedInUserId !== null && this.state.loggedInUserAccounts !== null) ? 
				(this.state.loggedInUserAccounts.length === 0 ? newUserComponents : loggedInComponents) : notLoggedInComponents}
				
			</div>
		)
	}
	
}
ReactDOM.render(<App />, document.getElementById("root"));