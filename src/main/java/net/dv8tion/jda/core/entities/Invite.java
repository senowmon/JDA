package net.dv8tion.jda.core.entities;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.impl.InviteImpl;
import net.dv8tion.jda.core.requests.RestAction;

import java.time.OffsetDateTime;

/**
 * Representation of a Discord Invite.
 * This class is immutable.
 *
 * @since  JDA 3.0
 * @author Aljoscha Grebe
 */
public interface Invite
{
    /**
     * Returns a new {@link Invite} for the given invite code.
     *
     * <br>Possible {@link net.dv8tion.jda.core.requests.ErrorResponse ErrorResponses}:
     * <ul>
     * <li>{@link net.dv8tion.jda.core.requests.ErrorResponse#UNKNOWN_INVITE Unknown Invite} <br>
     * The Message did not exist (possibly deleted)</li>
     * </ul>
     *
     * @param api
     *        The JDA object
     * @param code
     *        The invite code
     *
     * @throws net.dv8tion.jda.core.exceptions.ErrorResponseException
     *
     * @return {@link net.dv8tion.jda.core.requests.RestAction RestAction} -
     *
     *         <pre>
     * Type: {@link net.dv8tion.jda.core.entities.Invite Invite}
     *         <br>Value: {@code The Invite object}
     *         </pre>
     */
    public static RestAction<Invite> resolve(final JDA api, final String code)
    {
        return InviteImpl.resolve(api, code);
    }

    /**
     * Tries to return a new expanded {@link Invite} with more info.
     * <br>Requires either {@link net.dv8tion.jda.core.Permission#MANAGE_SERVER MANAGE_SERVER} in the invite's guild or
     * {@link net.dv8tion.jda.core.Permission#MANAGE_CHANNEL MANAGE_CHANNEL} in the invite's channel.
     * Will thorw a {@link net.dv8tion.jda.core.exceptions.PermissionException PermissionException} otherwise.
     *
     * @throws net.dv8tion.jda.core.exceptions.PermissionException
     *         if the account neither has {@link net.dv8tion.jda.core.Permission#MANAGE_SERVER MANAGE_SERVER} in the invite's guild nor
     *         {@link net.dv8tion.jda.core.Permission#MANAGE_CHANNEL MANAGE_CHANNEL} in the invite's channel
     *
     * @return {@link net.dv8tion.jda.core.requests.RestAction RestAction} -
     *
     *         <pre>
     * Type: {@link net.dv8tion.jda.core.entities.Invite Invite}
     *         <br>Value: {@code The expanded Invite object}
     *         </pre>
     *
     * @see Invite#isExpanded()
     */
    public RestAction<Invite> expand();

    /**
     * Returns an {@link Invite.Channel} object containing info about the invite's channel.
     *
     * @return The info about the invite's channel
     */
    Channel getChannel();

    /**
     * Returns the invite code
     *
     * @return the invite code
     */
    String getCode();

    /**
        * Returns creation date of this invite.
        * <br>
        * <br>This works only for expanded invites and will thorw a {@link IllegalStateException} otherwise!
        *
        * @throws IllegalStateException if this invite is not expanded
        *
        * @return The creation date of this invite
        *
        * @see Invite#expand()
        * @see Invite#isExpanded()
        */
    OffsetDateTime getCreationTime();

    /**
     * Returns an {@link Invite.Guild} object containing info about the invite's guild.
     *
     * @return The info about the invite's guild
     */
    Guild getGuild();

    /**
     * Returns the user who created this invite. This may be a fake user.
     * <br>
     * <br>This works only for expanded invites and will thorw a {@link IllegalStateException} otherwise!
     *
     * @throws IllegalStateException if this invite is not expanded
     *
     * @return The user who created this invite
     *
     * @see Invite#expand()
     * @see Invite#isExpanded()
     */
    User getInviter();

    /**
     * Returns the max age of this invite in seconds.
     * <br>
     * <br>This works only for expanded invites and will thorw a {@link IllegalStateException} otherwise!
     *
     * @throws IllegalStateException if this invite is not expanded
     *
     * @return The max age of this invite in seconds
     *
     * @see Invite#expand()
     * @see Invite#isExpanded()
     */
    int getMaxAge();

    /**
    * Returns the max uses of this invite. If there is no limit thius will return {@code 0}.
    * <br>
    * <br>This works only for expanded invites and will thorw a {@link IllegalStateException} otherwise!
    * 
    * @throws IllegalStateException if this invite is not expanded
    * 
    * @return The max uses of this invite or {@code 0} if there is no limit
    * 
    * @see Invite#expand()
    * @see Invite#isExpanded()
    */
    int getMaxUses();

    /**
     * Returns how often this invite has been used.
     * <br>
     * <br>This works only for expanded invites and will thorw a {@link IllegalStateException} otherwise!
     *
     * @throws IllegalStateException if this invite is not expanded
     *
     * @return The uses of this invite
     *
     * @see Invite#expand()
     * @see Invite#isExpanded()
     */
    int getUses();

    /**
     * Returns weather is invite expanded or not. Expanded invites contain more infos, but they can only be
     * obtained be {@link net.dv8tion.jda.core.entities.Guild#getInvites() Guild#getInvites()} (requires
     * {@link net.dv8tion.jda.core.Permission#MANAGE_CHANNEL Permission.MANAGE_CHANNEL}) or
     * {@link net.dv8tion.jda.core.entities.Channel#getInvites() Channel#getInvites()} (requires
     * {@link net.dv8tion.jda.core.Permission#MANAGE_SERVER Permission.MANAGE_SERVER}).
     *
     * <br>There's a covenience method {@link Invite#expand()} to get the expanded invite for an unexpanded one.
     *
     * @throws IllegalStateException if this invite is not expanded
     *
     * @return weather is invite expanded or not
     *
     * @see Invite#expand()
     */
    public boolean isExpanded();

    /**
     * Only expanded
     */
    boolean isTemporary();

    static interface Channel extends ISnowflake
    {
        /**
         * Returns the name of this channel.
         *
         * @return The channels's name
         *
         * @see {@link net.dv8tion.jda.core.entities.Channel#getName()}
         */
        String getName();

        /**
         * Returns the {@link net.dv8tion.jda.core.entities.ChannelType ChannelType} of this channel. <br>
         * Valid values are only {@link net.dv8tion.jda.core.entities.ChannelType#TEXT TEXT} or {@link net.dv8tion.jda.core.entities.ChannelType#VOICE VOICE}
         *
         * @return The channel's type
         *
         * @see {@link net.dv8tion.jda.core.entities.Channel#get}
         */
        ChannelType getType();
    }

    static interface Guild extends ISnowflake
    {
        /**
         * Returns the icon id of this guild.
         *
         * @return The guild's icon id
         *
         * @see {@link net.dv8tion.jda.core.entities.Guild#getIconId()}
         */
        String getIconId();

        /**
         * Returns the icon url of this guild.
         *
         * @return The guild's icon url
         *
         * @see {@link net.dv8tion.jda.core.entities.Guild#getIconId()}
         */
        String getIconUrl();

        /**
         * Returns the name of this guild.
         *
         * @return The guilds's name
         *
         * @see {@link net.dv8tion.jda.core.entities.Guild#getName()}
         */
        String getName();

        /**
         * Returns the splash image id of this guild.
         *
         * @return The guild's splash image id or {@code null} if the guild has no splash image
         *
         * @see {@link net.dv8tion.jda.core.entities.Guild#getSplashId()}
         * @see {@link Invite.Guild#getSplashUrl()}
         */
        String getSplashId();

        /**
         * Returns the splash image url of this guild.
         *
         * @return The guild's splash image url or {@code null} if the guild has no splash image
         *
         * @see {@link net.dv8tion.jda.core.entities.Guild#getSplashId()}
         * @see {@link Invite.Guild#getSplashId()}
         */
        String getSplashUrl();
    }
}