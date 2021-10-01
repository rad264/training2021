function CheckBalanceForm(props) {
    const accounts = props.accounts;
	return (
	
        <div>
            <h3>Check Balance</h3>
            <label for="accountNumber">Account Number:</label>
				<select className="accountNumber" name="accountNumber" value={props.accountNumber} onChange={props.onChange}>
					<option value="" disabled selected hidded>---Select an Account Number---</option>
					{accounts.map((account) => (
						<option>{account}</option>
					))}
					
				</select>
            <button onClick={props.onClick} className="button">Check Balance</button>
        </div>
    );
}