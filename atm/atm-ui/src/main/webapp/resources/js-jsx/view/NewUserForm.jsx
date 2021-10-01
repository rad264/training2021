function NewUserForm(props) {
    return (
        <div>
            <h3>Signup</h3>
            <label for="userId">User ID:</label>
            <input type="text" name="userId" onChange={props.onChange} value={props.userId} placeholder="Enter New User ID" />
            <button onClick={props.onClick} className="button">Create User</button>
        </div>
    );
}