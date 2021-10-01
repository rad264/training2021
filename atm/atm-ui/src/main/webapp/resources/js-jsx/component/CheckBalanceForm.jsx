function CheckBalanceForm(props) {
    return (
        <div>
            <h3>Check Balance</h3>
            <label for="accountNumber">AccountNumber:</label>
            <input type="text" name="accountNumber" onChange={props.onChange} value={props.accountNumber} />
            <button onClick={props.onClick}>Check Balance</button>
        </div>
    );
}