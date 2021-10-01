class NewAccountController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new NewAccountModel();
        this.onChange = this.onChange.bind(this);
        this.openNewAccount = this.openNewAccount.bind(this);
    }
    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
    }
    openNewAccount() {
        const userId = this.props.getUser();
		const amount = this.state.amount
		const currentAccounts = this.props.getAccounts();
        let handleResponse = (status, message, newAccountNumber) => this.setState({responseStatus: status, message: message, newAccountNumber: newAccountNumber});
        handleResponse = handleResponse.bind(this);
		let updateAccounts = this.props.updateAccounts;
		updateAccounts = updateAccounts.bind(this);
        $.ajax({
            url: "/atm-api/users/" + userId + "/accounts/new-account",
            type: "POST",
			headers: { 
        		'Accept': 'application/json',
        		'Content-Type': 'application/json' 
    		},
			data: amount,
            success: function(response) {
				console.log(response);
				currentAccounts.push(response) 
                handleResponse(200, "Account created");
				updateAccounts(currentAccounts);
				alert("Account successfully created.");
				

            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status, "Account not created");
				alert("Account could not be created.");
            }
        });
    }
    render() {
        return (
            <div>
                <NewAccountForm amount={this.state.amount} onChange={this.onChange} onClick={this.openNewAccount} />
                <NewAccount statusCode={this.state.responseStatus} message={this.state.message} />
            </div>
        );
    }
}