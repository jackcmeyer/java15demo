package com.jackcmeyer.java15demo;

import javax.validation.constraints.NotNull;

public record PersonRequest(@NotNull String name, @NotNull  String occupation) {}
