function LoginForm(props) {
    return (
        <div>
            <h3>Login</h3>
            <label for="userId">User ID:</label>
            <input type="text" name="userId" onChange={props.onChange} value={props.userId} placeholder="Enter User ID" />
            <button onClick={props.onClick} className="button">Login</button>
        </div>
    );
}