function Balance(props) {
    let error;
	let resultText;
	props.balance !== undefined ? resultText = "Balance (USD): " : resultText = "";
    if (props.statusCode && props.statusCode !== 200)
        error = props.statusCode === 404 ? "Account Not Found" : "Unexpected Error";
    return <p>{resultText}<span style={{color: error ? "red" : "green"}}>{error || props.balance}</span></p>;
}