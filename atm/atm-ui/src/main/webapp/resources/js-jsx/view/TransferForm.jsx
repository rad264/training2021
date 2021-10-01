function TransferForm(props) {
	const accounts = props.accounts;
    return (
        <div>
            <h3>Transfer</h3>
            <label for="accountNumber">From:</label>
				<select className="accountNumber" name="accountNumber" value={props.accountNumber} onChange={props.onChange}>
					<option value="" disabled selected hidded>---Select an Account Number---</option>
					{accounts.map((account) => (
						<option>{account}</option>
					))}
				</select>
			<label for="accountNumber">To:</label>
				<select className="accountNumber" name="accountNumberToTransfer" value={props.accountNumberToTransfer} onChange={props.onChange}>
					<option value="" disabled selected hidded>---Select an Account Number---</option>
					{accounts.map((account) => (
						<option>{account}</option>
					))}
				</select>
			<label for="amount">Amount (USD):</label>
			<input type="text" name="amount" onChange={props.onChange} value={props.amount} placeholder="Enter Amount" />
            <button onClick={props.onClick} className="button">Transfer</button>
        </div>
    );
}