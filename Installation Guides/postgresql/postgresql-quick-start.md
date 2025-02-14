
## 1. Install PostgreSQL 

1. **Download PostgreSQL** from:  
   [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

2. **Run the installer**:
   - On Windows: In the wizard, you can **uncheck pgAdmin** if you don’t need it. I recommend using DBeaver.
   - Provide a **password** for the PostgreSQL superuser (default username `postgres`). I recommend a simple password, like `password`.
   - Accept or change the default port (typically `5432`). Default is recommended since everyone uses that.

3. When the installation completes, note the **installation directory** (e.g.,  
   `C:\Program Files\PostgreSQL\15\` on Windows).

---

## 2. Add `psql` to Your PATH

### Windows

1. **Find the `psql` bin folder**:  
   For example, if you installed PostgreSQL in `C:\Program Files\PostgreSQL\15\`, the bin folder is typically  
   `C:\Program Files\PostgreSQL\15\bin`.

2. **Open Environment Variables**:
   - Right-click the **Start** button (or press `Win+X`) and choose **System**.
   - In the “System” window, click **Advanced system settings** (or “System info” → “Advanced system settings”).
   - In the “System Properties” dialog, click **Environment Variables…**.

3. **Edit the PATH variable**:
   - Under **System variables**, find **Path** and select **Edit**.
   - Click **New**, then **paste** the bin folder path (e.g.,  
     `C:\Program Files\PostgreSQL\15\bin`).
   - Click **OK** to close each dialog.

4. **Restart any open command prompts/terminals** for the changes to take effect.

### macOS

1. Open a terminal and edit your shell config file (e.g., `~/.bash_profile`, `~/.zshrc`, or `~/.bashrc`).
2. Add a line like:
   ```bash
   export PATH="/Library/PostgreSQL/15/bin:$PATH"
   ```
   Adjust the directory if needed.  
3. **Save** the file and run `source ~/.zshrc` (or the appropriate file) to apply changes immediately.

---

## 3. Create a Database Using `psql`

Now that `psql` is in your PATH, you can open a new terminal or command prompt and run it from **anywhere**.

1. **Open a new terminal or command prompt**.
2. **Log in** to PostgreSQL as the superuser (`postgres`):
   ```bash
   psql -U postgres
   ```
   If prompted, enter the `postgres` password you set during installation.

3. **Create a database** (e.g., `my_database`, `p0`, or `demos`):
   ```sql
   CREATE DATABASE my_database;
   ```
4. Type `\q` to **quit** psql.

---

## 4. Install and Launch DBeaver

1. **Download DBeaver** (Community Edition) from:  
   [https://dbeaver.io/download/](https://dbeaver.io/download/)  
2. **Install DBeaver**:
   - Windows: Run the `.exe` installer.
   - macOS: Drag the `.app` to Applications.
   - Linux: Use `.deb`, `.rpm`, or snap/flatpak packages.
3. **Open DBeaver** once installation is complete.

---

## 5. Connect to PostgreSQL Using DBeaver

1. In DBeaver, click **Database** → **New Connection** (or the `New Connection` button).
2. Select **PostgreSQL** from the list.
3. In the **Connection settings** window:
   - **Host**: `localhost`
   - **Port**: `5432` (unless you changed it)
   - **Database**: `my_database`
   - **Username**: `postgres`
   - **Password**: the password set during step #2 or for the new user
4. Click **Test Connection**. If it succeeds, click **Finish**.

---

## 6. Verify Your Database

1. In DBeaver’s **Database Navigator**, expand your new connection.
2. You’ll see your **database** → **Schemas** → `public` (by default).
3. Right-click and **Refresh** if you don’t see your tables or any updates.

---

## 7. Create a `users` Table and Insert Data from the SQL Editor

Now let’s add a simple table and insert some data directly in DBeaver:

1. **Open a new SQL script** in DBeaver:
   - Click on the **SQL Editor** button in the main toolbar, **or**
   - Right-click your database in the **Database Navigator** → **SQL Editor** → **New SQL Script**.
   - Ensure the correct database connection is selected at the top of the editor.

2. **Create a `users` table** by running this SQL:
   ```sql
   CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100)
   );
   ```
   - Press the **Execute** button (or `Ctrl+Enter` on Windows / `Cmd+Enter` on macOS) to run the script.

3. **Insert a sample record**:
   ```sql
   INSERT INTO users (name, email)
   VALUES ('Alice', 'alice@example.com');
   ```

4. **View the inserted data**:
   ```sql
   SELECT * FROM users;
   ```
   - Execute this query to see the newly inserted record.

5. **Refresh** the Database Navigator if needed to see your new `users` table under the `public` schema.

That’s it! You’ve now created a database, connected to it via DBeaver, and used the SQL editor to create a table and insert a row of data—all without using pgAdmin.