package com.lemoncog.blindreads.test.factory;

import com.lemoncog.blindreads.controllers.LoginController;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.models.User;

import static org.mockito.Mockito.*;
/**
 * Created by Gaming on 25/01/14.
 */
public class MockFactory {

    public static IUser getMockUserWithID(String userID)
    {
        IUser user = mock(IUser.class);

        when(user.getUserID()).thenReturn(userID);

        return user;
    }
}
