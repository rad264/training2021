class LoginController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new LoginModel();
        this.onChange = this.onChange.bind(this);
        this.getAccounts = this.getAccounts.bind(this);
    }
    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
	
    }
    getAccounts() {
        const userId = this.state.userId;
        let handleResponse = (status, accounts) => this.setState({responseStatus: status, accounts: accounts});
        handleResponse = handleResponse.bind(this);
		let updateAccounts = this.props.updateAccounts;
		updateAccounts = updateAccounts.bind(this);
		let updateUser = this.props.updateUser;
		updateUser = updateUser.bind(this);
        $.ajax({
            url: "/atm-api/users/" + userId,
            type: "GET",
            success: function(response) {
				let accountsResults = [];
				for (let i = 0; i < response.accounts.length; i++) {
					accountsResults[i] = response.accounts[i];
				}
                handleResponse(200, accountsResults);
				updateUser(userId);
				updateAccounts(accountsResults);
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status);
            }
        });
    }
    render() {
        return (
            <div>
                <LoginForm userId={this.state.userId} onChange={this.onChange} onClick={this.getAccounts} />
                <Login statusCode={this.state.responseStatus} accounts={this.state.accounts} />
            </div>
        );
    }
}
//ReactDOM.render(<GetAccountsController />, document.getElementById("root"));