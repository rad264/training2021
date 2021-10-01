function Withdraw(props) {
    let error;
    if (props.statusCode && props.statusCode !== 200)
        error = props.statusCode === 400 ? "Withdrawal unsuccessful." : "Unexpected Error";
    return <p><span style={{color: error ? "red" : "green"}}>{error || props.message}</span></p>;
}