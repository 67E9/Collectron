package de.yougrowgroup.CollectronBackend.Model;

import lombok.Data;

@Data
public class PasswordPair {
    private String oldPassword;
    private String newPassword;
}
