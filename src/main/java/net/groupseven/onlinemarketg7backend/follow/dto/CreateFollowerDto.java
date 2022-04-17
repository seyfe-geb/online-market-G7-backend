package net.groupseven.onlinemarketg7backend.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateFollowerDto {
    @NotNull
    private  long followeeId;
}
