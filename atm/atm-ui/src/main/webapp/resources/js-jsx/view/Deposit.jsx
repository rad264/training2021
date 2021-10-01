function Deposit(props) {
    let error;
    if (props.statusCode && props.statusCode !== 200)
        error = props.statusCode === 400 ? "Deposit unsuccessful." : "Unexpected Error";
    return <p><span style={{color: error ? "red" : "green"}}>{error || props.message}</span></p>;
}