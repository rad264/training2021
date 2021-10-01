function NewAccount(props) {
    let error;
    if (props.statusCode && props.statusCode !== 200)
        error = props.statusCode === 400 ? "Account not created. Amount must be a positive number." : "Unexpected Error";
    return <p><span style={{color: error ? "red" : "green"}}>{error || props.message}</span></p>;
}