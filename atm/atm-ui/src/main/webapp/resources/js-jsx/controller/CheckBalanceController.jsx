class CheckBalanceController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new CheckBalanceModel();
		this.state = {accounts : this.props.getAccounts()};
        this.onChange = this.onChange.bind(this);
        this.getBalance = this.getBalance.bind(this);

    }
    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
    }

    getBalance() {
        const accountNumber = this.state.accountNumber;
        let handleResponse = (status, balance) => this.setState({responseStatus: status, balance: balance});
        handleResponse = handleResponse.bind(this);
        $.ajax({
            url: "/atm-api/accounts/" + accountNumber,
            type: "GET",
            success: function(response) {
                handleResponse(200, response.balance);
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status);
            }
        });
    }
    render() {
        return (
            <div>
                <CheckBalanceForm accountNumber={this.state.accountNumber} accounts={this.state.accounts} onChange={this.onChange} onClick={this.getBalance} />
                <Balance statusCode={this.state.responseStatus} balance={this.state.balance} />
            </div>
        );
    }
}
//ReactDOM.render(<CheckBalanceController />, document.getElementById("root"));