class WithdrawController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new WithdrawModel();
	this.state = {accounts : this.props.getAccounts()};
        this.onChange = this.onChange.bind(this);
        this.withdraw = this.withdraw.bind(this);
    }

    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
    }

    withdraw() {
        const accountNumber = this.state.accountNumber;	
		const amount = this.state.amount;
        let handleResponse = (status, message) => this.setState({responseStatus: status, message: message});
        handleResponse = handleResponse.bind(this);
        $.ajax({
            url: "/atm-api/accounts/" + accountNumber + "/withdrawals",
            type: "POST",
			headers: { 
        		'Accept': 'application/json',
        		'Content-Type': 'application/json' 
    		},
			data: amount,
            success: function(response) {
                handleResponse(200, "Withdrawal successful.");
				alert("Withdrawal successful.");
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status, "Withdrawal could not be processed.");
				alert("Withdrawal could not be processed. Please make sure to enter a positive amount that is not greater than your current balance.");
            }
        });
    }
    render() {
        return (
            <div>
                <WithdrawForm accountNumber={this.state.accountNumber} accounts={this.state.accounts} amount={this.state.amount} onChange={this.onChange} onClick={this.withdraw} />
                <Withdraw statusCode={this.state.responseStatus} message={this.state.message} />
            </div>
        );
    }
}