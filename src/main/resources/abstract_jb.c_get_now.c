static long get_now(struct ast_jb *jb, struct timeval *when)
{
	struct timeval now;
	if (!when) {
		when = &now;
		gettimeofday(when, NULL);
	}
	return ast_tvdiff_ms(*when, jb->timebase);
}
