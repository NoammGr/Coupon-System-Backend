package app.core.servcies;

import app.core.auth.UserCredentials;

public abstract class ClientService {

    abstract boolean login(UserCredentials userCredentials);
}
