package net.groupseven.onlinemarketg7backend.follow.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateFollowerDto {
    @NotNull
    private  long followeeId;
}
